package webapp.storage;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import webapp.Config;
import webapp.exeption.ExistStorageExeption;
import webapp.exeption.NotExistStorageExeption;
import webapp.model.*;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class AbstractStorageTest {

    protected Storage storage;
    protected static final File STORAGE_DIR = Config.getInstance().getStorageDir();

    private static final String  UUID1= UUID.randomUUID().toString();
    private static final String  UUID2= UUID.randomUUID().toString();
    private static final String  UUID3= UUID.randomUUID().toString();
    private static final String  UUID4= UUID.randomUUID().toString();

    private static final Resume R1;
    private static final Resume R2;
    private static final Resume R3;

    static {
       R1 = new Resume(UUID1, "FullName1");
       R2 = new Resume(UUID2, "FullName2");
       R3 = new Resume(UUID3, "FullName3");


       R1.addContact(ContactType.EMAIL,"FullName1@mail.ru");
       R1.addContact(ContactType.PHONE,"+7-918-111-11-11");
       R1.addContact(ContactType.WEBSITE,"http://www.UUID1.ru");
       R1.addContact(ContactType.SKYPE,"UUID1_SKYPE");
       R1.addSection(SectionType.PERSONAL,new TextSection("Personal information: I realy intelligent and creative person"));
       R1.addSection(SectionType.OBJECTIVE,new TextSection("Objective: To be honerst, i want to find a great job where I can do anything what i want"));
       R1.addSection(SectionType.ACHIEVEMENT, new ListSection("took part in the hard project","implemeted java anywhere","satistfied everybody"));
       R1.addSection(SectionType.QUALIFICATIONS,new ListSection("really great","best of the best","never give up") );
       R1.addSection(SectionType.EXPERIENCE, new OrganizationSection(
               new Organization("the best work place","",
                    new Organization.Position( LocalDate.of(2001,01,12), LocalDate.of(2003,02,26),"developer","coding"),
                    new Organization.Position( LocalDate.of(2003,01,12), LocalDate.of(2005,02,26),"developer2","coding2")),
               new Organization("the best work place2","http://the bestplace.ru",
                       new Organization.Position( LocalDate.of(2007,01,12), LocalDate.of(2010,02,2),"developer","coding"))
       ));

        R1.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("The best Institute in the world","http://the bestEducation.ru",
                        new Organization.Position( LocalDate.of(1996,01,12), LocalDate.of(2001,02,26),"Engineer","coding")),
                new Organization("The best School in the world","http://the bestSchool.ru",
                        new Organization.Position( LocalDate.of(2001,01,12), LocalDate.of(2003,02,26),"developer","coding"))
        ));
       R2.addContact(ContactType.EMAIL,"FullName2@mail.ru");
       R2.addContact(ContactType.PHONE,"+7-918-222-22-22");
       R2.addContact(ContactType.WEBSITE,"http://www.UUID2.ru");
       R2.addContact(ContactType.SKYPE,"UUID2_SKYPE");
       R2.addSection(SectionType.PERSONAL,TextSection.EMPTY);
       R2.addSection(SectionType.OBJECTIVE,TextSection.EMPTY);
       R2.addSection(SectionType.QUALIFICATIONS,ListSection.EMPTY);
       R2.addSection(SectionType.ACHIEVEMENT,ListSection.EMPTY);
       R2.addSection(SectionType.EDUCATION,new OrganizationSection(Organization.EMPTY));
       R2.addSection(SectionType.EXPERIENCE,new OrganizationSection(Organization.EMPTY));

        R3.addContact(ContactType.EMAIL,"FullName3@mail.ru");
        R3.addContact(ContactType.PHONE,"+7-918-333-33-333");
        R3.addContact(ContactType.WEBSITE,"http://www.UUID3.ru");
        R3.addContact(ContactType.SKYPE,"UUID3_SKYPE");
        R3.addSection(SectionType.PERSONAL,TextSection.EMPTY);
        R3.addSection(SectionType.OBJECTIVE,TextSection.EMPTY);
        R3.addSection(SectionType.QUALIFICATIONS,ListSection.EMPTY);
        R3.addSection(SectionType.ACHIEVEMENT,ListSection.EMPTY);
        R3.addSection(SectionType.EDUCATION,new OrganizationSection(Organization.EMPTY));
        R3.addSection(SectionType.EXPERIENCE,new OrganizationSection(Organization.EMPTY));

    }


    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
       storage.clear();
       storage.save(R1);
       storage.save(R2);
       storage.save(R3);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() throws Exception {
        assertEquals(R1);
        assertEquals(R2);
        assertEquals(R3);
    }

    @Test(expected = NotExistStorageExeption.class)
    public void getNotExist() throws Exception {
        storage.get("uuid_Not_Exist");
    }

    @Test(expected = NotExistStorageExeption.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("uuid_Not_Exist", "Empty"));//get NotExistStorageExeption
    }

    @Test(expected = NotExistStorageExeption.class)
    public void deleteNotExist() throws Exception {
        storage.delete("uuid_Not_Exist");
    }


    @Test(expected = ExistStorageExeption.class)
    public void saveExistTest() throws Exception {
        storage.save(new Resume(UUID1, "Empty")); //get ExistStorageExeption
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume resumeForUpdate = getNewResume(UUID1,UUID1);
        storage.update(resumeForUpdate);
        Assert.assertTrue(resumeForUpdate.equals(storage.get(resumeForUpdate.getUuid())));
    }



    @Test
    public void save() throws Exception {
        Resume resumeForSave = getNewResume(UUID4,UUID4);
        storage.save(resumeForSave);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(resumeForSave.getUuid(), storage.get(resumeForSave.getUuid()).getUuid());
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID2);
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> myLst = storage.getAllSorted();
        Assert.assertEquals(3, myLst.size());
        Assert.assertEquals(Arrays.asList(R1,R2,R3),myLst );
    }

    private void assertEquals (Resume r){
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    private Resume getNewResume(String uuid, String full_name) {

        Resume resume = new Resume(uuid, full_name);
        resume.addContact(ContactType.SKYPE,"myskype");
        resume.addContact(ContactType.PHONE,"124241");
        resume.addContact(ContactType.EMAIL,"WWW@bezdna.ru");

        resume.addSection(SectionType.PERSONAL,new TextSection("Personal information: UPDATE!!!"));
        resume.addSection(SectionType.OBJECTIVE,new TextSection("Objective: UPDATE!!!"));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection("ACHIEVEMENT-Update1","ACHIEVEMENT-Update2","ACHIEVEMENT-update3"));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection("QUALIFICATIONS-Update1","QUALIFICATIONS-Update2","QUALIFICATIONS-update3"));
       resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("EXPERIENCE1","",
                        new Organization.Position( LocalDate.of(2001,01,12), LocalDate.of(2003,02,26),"developer","coding"),
                        new Organization.Position( LocalDate.of(2003,01,12), LocalDate.of(2005,02,26),"developer2","coding2")),
                new Organization("EXPERIENCE2","http://the bestplace.ru",
                        new Organization.Position( LocalDate.of(2007,01,12), LocalDate.of(2010,02,26),"developer","coding"))
        ));

        resume.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("EDUCATION1","http://the bestEducation.ru",
                        new Organization.Position( LocalDate.of(1996,01,12), LocalDate.of(2001,02,26),"Engineer","coding")),
                new Organization("EDUCATION2","http://the bestSchool.ru",
                        new Organization.Position( LocalDate.of(2001,01,12), LocalDate.of(2003,02,26),"developer","coding"))));
        return resume;
    }

}
