-- public."user" definition

-- Drop table

-- DROP TABLE public."user";

CREATE TABLE public."user" (
	id uuid NULL,
	"name" varchar NULL,
	age int8 NULL,
	createdat date NULL,
	updatedat date NULL
);
