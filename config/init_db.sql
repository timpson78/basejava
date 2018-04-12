
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

CREATE TABLE section
(
  id          SERIAL   NOT NULL
    CONSTRAINT section_id_pk
    PRIMARY KEY,
  resume_uuid CHAR(36) NOT NULL
    CONSTRAINT section_resume_uuid_fk
    REFERENCES resume
    ON DELETE CASCADE,
  type        TEXT     NOT NULL,
  value       TEXT
);
CREATE UNIQUE INDEX section_id_uindex
  ON section (id);


CREATE TABLE organization
(
  resume_uuid          CHAR(36) NOT NULL
    CONSTRAINT organization_resume_uuid_fk
    REFERENCES resume
    ON DELETE CASCADE,
  linkname             TEXT,
  id                   SERIAL   NOT NULL
    CONSTRAINT organization_id_pk
    PRIMARY KEY,
  linkurl              TEXT,
  type                 TEXT,
  title_position       TEXT,
  description_position TEXT,
  startdate_position   TIMESTAMP,
  enddate_position     TIMESTAMP
);
CREATE UNIQUE INDEX organization_id_uindex
  ON organization (id);
