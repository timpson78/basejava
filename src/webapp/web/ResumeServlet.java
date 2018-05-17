package webapp.web;


import webapp.Config;
import webapp.model.*;

import webapp.storage.Storage;
import webapp.utils.DateUtil;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage=Config.getInstance().getSqlStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid=request.getParameter("uuid");
        String fullname=request.getParameter("fullname");
        Resume r;

        final boolean isCreate=(uuid==null|| uuid.length()==0);
        if (isCreate) {
            r=new Resume(fullname);
        } else{
            r = storage.get(uuid);
            r.setFullName(fullname);
        }

        for (ContactType type: ContactType.values()){
            String value=request.getParameter(type.name());
            if (value!=null && value.trim().length()!=0) {
                  r.addContact(type,value);
            } else {
                r.getContacts().remove(type);
            }
        }

        for ( SectionType sectionType: SectionType.values())
        {
            String value=request.getParameter(sectionType.name());

            if (value!=null && value.trim().length()!=0) {
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        TextSection textSection= new TextSection(value);
                        r.addSection(sectionType,textSection);
                    break;
                    case QUALIFICATIONS:
                    case ACHIEVEMENT:
                        List<String> list = new ArrayList<String>(Arrays.asList(value.trim().split("\n")));
                            //ist.removeAll(Arrays.asList(" ","\r"," \r"));
                            ListSection listSection = new ListSection(list);
                            r.addSection(sectionType,listSection);
                    break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> orgList = new ArrayList<>();
                        String[] organizationName=request.getParameterValues(sectionType.name()+"_org");
                        if (organizationName!=null){
                            String[] organizationUrl=request.getParameterValues(sectionType.name()+"_url");
                            for (int i=0;i<=organizationName.length-1; i++) {

                                List<Organization.Position> listPos = new ArrayList<Organization.Position>();
                                if (organizationName[i] != null && organizationName[i] != "") {
                                    String[] title = request.getParameterValues(sectionType.name() + "_title_" + i);
                                    String[] description = request.getParameterValues(sectionType.name() + "_description_" + i);
                                    String[] startdate = request.getParameterValues(sectionType.name() + "_startdate_" + i);
                                    String[] enddate = request.getParameterValues(sectionType.name() + "_enddate_" + i);

                                    for (int j = 0; j <= title.length - 1; j++) {
                                        if (title[j] != null && title[j] != "") {
                                            listPos.add(new Organization.Position(DateUtil.Parse(startdate[j]), DateUtil.Parse(enddate[j]), title[j], description[j]));
                                        }
                                    }
                                }
                                orgList.add(new Organization(organizationName[i], organizationUrl[i], listPos));
                            }
                        }
//for new organization
                        String newOrg=request.getParameter(sectionType.name()+"_org_new");
                        if   (newOrg!="" && newOrg!=null) {
                            orgList.add(new Organization(newOrg,
                                    request.getParameter(sectionType.name() + "_url_new"),
                                    new Organization.Position(DateUtil.Parse(request.getParameter(sectionType.name() + "_startdate_new")),
                                            DateUtil.Parse(request.getParameter(sectionType.name() + "_enddate_new")),
                                            request.getParameter(sectionType.name() + "_title_new"),
                                            request.getParameter(sectionType.name() + "_description_new"))));
                        }
//for new organization
                        OrganizationSection organizationSection = new OrganizationSection(orgList);
                        r.addSection(sectionType, organizationSection);
                    break;
                }
            }
        }

        if (isCreate){
            storage.save(r);
        }else {
            storage.update(r);
        }
        response.sendRedirect("resumes");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             String uuid=request.getParameter("uuid");
             String action=request.getParameter("action");
             if (action==null) {
                 request.setAttribute("resumes", storage.getAllSorted());
                 request.setAttribute("sections", SectionType.values());
                 request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
             } else {
                 Resume r;
                 switch(action) {
                     case "delete":
                         storage.delete(uuid);
                         response.sendRedirect("/resumes");
                         return;
                     case "view":
                         r=storage.get(uuid);
                         break;
                     case "add":
                         r=Resume.EMPTY;
                         break;
                     case "edit":
                         r=storage.get(uuid);
                         for (SectionType type : SectionType.values()) {
                             Section section = r.getSection(type);
                             switch (type) {
                                 case OBJECTIVE:
                                 case PERSONAL:
                                     if (section == null) {
                                         section = TextSection.EMPTY;
                                     }
                                     break;
                                 case ACHIEVEMENT:
                                 case QUALIFICATIONS:
                                     if (section == null) {
                                         section = ListSection.EMPTY;
                                     }
                                     break;
                                 case EXPERIENCE:
                                 case EDUCATION:
                                     if (section==null) {
                                         OrganizationSection orgSection = (OrganizationSection) section;
                                         List<Organization> emptyFirstOrganizations = new ArrayList<>();
                                         emptyFirstOrganizations.add(Organization.EMPTY);
                                         if (orgSection != null) {
                                             for (Organization org : orgSection.getOrgList()) {
                                                 List<Organization.Position> emptyFirstPositions = new ArrayList<>();
                                                 emptyFirstPositions.add(Organization.Position.EMPTY);
                                                 emptyFirstPositions.addAll(org.getPositions());
                                                 emptyFirstOrganizations.add(new Organization(org.getOrgLink(), emptyFirstPositions));
                                             }
                                         }
                                         section = new OrganizationSection(emptyFirstOrganizations);
                                     }
                                     break;
                             }
                             r.addSection(type, section);
                         }
                         break;
                     default:
                         throw new IllegalArgumentException("Action"+action+"illegal");
                 }

                 request.setAttribute("resume", r);
                 request.getRequestDispatcher(("view".equals(action)? "/WEB-INF/jsp/view.jsp":"/WEB-INF/jsp/edit.jsp")
                 ).forward(request, response);
             }
    }


}
