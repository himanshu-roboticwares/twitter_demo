# twitter_demo
Sample application using angularjs, postgresql and spring-boot
Pre-requisites for the project.

1. Create table "users" in postgresql

Query: CREATE TABLE users
(
  email character varying(256) NOT NULL,
  username serial NOT NULL,
  name character varying(256),
  password character varying(256),
  profileimgurl character varying(256),
  phone character varying(15),
  acstatus character varying(250),
  CONSTRAINT users_pkey PRIMARY KEY (email)
);

2. Create table "tweets" in postgresql

Query: CREATE TABLE tweets
(
  username integer NOT NULL,
  message character varying(250),
  timest timestamp without time zone NOT NULL,
  CONSTRAINT tweets_pkey PRIMARY KEY (username, timest)
);

3. Create table "follower" in postgresql

Query: CREATE TABLE follower
(
  id serial NOT NULL,
  username integer,
  followsto integer,
  CONSTRAINT follower_pkey PRIMARY KEY (id)
);

4. Create sequence "hibernate_sequence" in postgresql

Query: CREATE SEQUENCE hibernate_sequence
  INCREMENT 1
  MINVALUE 1


5. IDE, I had used: Intellij IDEA
