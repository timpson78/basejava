package webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    SKYPE("Skype"){
        @Override
        public String toHtml0(String value) {
            return "<a href='skype:"+ value+"'>"+value+"</a>";
        }
    }
    ,
    EMAIL("Почта"){
        @Override
        public String toHtml0(String value) {
            return "<a href='mailto:"+ value+"'>"+value+"</a>";
        }
    },
    WEBSITE("Сайт"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHub"),
    STACKOVERFLOW("StackOverflow");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String toHtml0(String value) {
        return  title+":"+ value;
    }
    public String toHtml(String value) {
        return (value==null)? "": toHtml0(value);
    }

}

