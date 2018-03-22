
CREATE TABLE resume
(
  uuid      CHAR(36) NOT NULL
    CONSTRAINT resume_pkey
    PRIMARY KEY,
  full_name TEXT NOT NULL
);

CREATE TABLE contact
(
  id         SERIAL   NOT NULL
    CONSTRAINT contact_pkey
    PRIMARY KEY,
  resume_uuid CHAR(36) NOT NULL    CONSTRAINT contact_resume_uuid_fk REFERENCES resume ON DELETE CASCADE,
  type       TEXT     NOT NULL,
  value      TEXT     NOT NULL

);
CREATE UNIQUE INDEX uuid_resume_uuid__index ON public.contact (resume_uuid, type);