CREATE TABLE IF NOT EXISTS feeds (
  id varchar(256) PRIMARY KEY,
  creator_id varchar(256) NOT NULL,
  text text NOT NULL,
  created_at timestamp NOT NULL DEFAULT now()
);
