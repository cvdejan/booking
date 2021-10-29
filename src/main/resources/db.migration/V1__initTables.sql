--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: booking; Type: SCHEMA; Schema: -; Owner: dejan
--

CREATE SCHEMA booking;


ALTER SCHEMA booking OWNER TO dejan;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: app_user; Type: TABLE; Schema: booking; Owner: dejan
--

CREATE TABLE booking.app_user (
    id bigint NOT NULL,
    address character varying(255),
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    user_name character varying(255) NOT NULL
);


ALTER TABLE booking.app_user OWNER TO dejan;

--
-- Name: app_user_id_seq; Type: SEQUENCE; Schema: booking; Owner: dejan
--

CREATE SEQUENCE booking.app_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE booking.app_user_id_seq OWNER TO dejan;

--
-- Name: app_user_id_seq; Type: SEQUENCE OWNED BY; Schema: booking; Owner: dejan
--

ALTER SEQUENCE booking.app_user_id_seq OWNED BY booking.app_user.id;


--
-- Name: app_user_roles; Type: TABLE; Schema: booking; Owner: dejan
--

CREATE TABLE booking.app_user_roles (
    app_user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE booking.app_user_roles OWNER TO dejan;

--
-- Name: booking; Type: TABLE; Schema: booking; Owner: dejan
--

CREATE TABLE booking.booking (
    id bigint NOT NULL,
    adults integer NOT NULL,
    check_in timestamp without time zone NOT NULL,
    check_out timestamp without time zone NOT NULL,
    children integer NOT NULL,
    city_id integer NOT NULL,
    country_id integer NOT NULL,
    customer_name character varying(255) NOT NULL,
    dob timestamp without time zone NOT NULL,
    extra_bed boolean NOT NULL,
    gender character varying(255) NOT NULL,
    guest1age character varying(255) NOT NULL,
    guest1gender character varying(255) NOT NULL,
    guest1name character varying(255) NOT NULL,
    guest2age character varying(255),
    guest2gender character varying(255),
    guest2name character varying(255),
    hotel_id integer NOT NULL,
    is_expanded boolean NOT NULL,
    phone character varying(255) NOT NULL,
    room_type_id integer NOT NULL,
    status character varying(255) NOT NULL
);


ALTER TABLE booking.booking OWNER TO dejan;

--
-- Name: booking_dine_in; Type: TABLE; Schema: booking; Owner: dejan
--

CREATE TABLE booking.booking_dine_in (
    owner_id bigint NOT NULL,
    dine_in boolean
);


ALTER TABLE booking.booking_dine_in OWNER TO dejan;

--
-- Name: booking_foods; Type: TABLE; Schema: booking; Owner: dejan
--

CREATE TABLE booking.booking_foods (
    owner_id bigint NOT NULL,
    food character varying(255)
);


ALTER TABLE booking.booking_foods OWNER TO dejan;

--
-- Name: booking_id_seq; Type: SEQUENCE; Schema: booking; Owner: dejan
--

CREATE SEQUENCE booking.booking_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE booking.booking_id_seq OWNER TO dejan;

--
-- Name: booking_id_seq; Type: SEQUENCE OWNED BY; Schema: booking; Owner: dejan
--

ALTER SEQUENCE booking.booking_id_seq OWNED BY booking.booking.id;


--
-- Name: city; Type: TABLE; Schema: booking; Owner: dejan
--

CREATE TABLE booking.city (
    id bigint NOT NULL,
    city_name character varying(100) NOT NULL
);


ALTER TABLE booking.city OWNER TO dejan;

--
-- Name: city_id_seq; Type: SEQUENCE; Schema: booking; Owner: dejan
--

CREATE SEQUENCE booking.city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE booking.city_id_seq OWNER TO dejan;

--
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: booking; Owner: dejan
--

ALTER SEQUENCE booking.city_id_seq OWNED BY booking.city.id;


--
-- Name: country; Type: TABLE; Schema: booking; Owner: dejan
--

CREATE TABLE booking.country (
    id bigint NOT NULL,
    country_name character varying(100) NOT NULL
);


ALTER TABLE booking.country OWNER TO dejan;

--
-- Name: country_id_seq; Type: SEQUENCE; Schema: booking; Owner: dejan
--

CREATE SEQUENCE booking.country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE booking.country_id_seq OWNER TO dejan;

--
-- Name: country_id_seq; Type: SEQUENCE OWNED BY; Schema: booking; Owner: dejan
--

ALTER SEQUENCE booking.country_id_seq OWNED BY booking.country.id;


--
-- Name: hotel; Type: TABLE; Schema: booking; Owner: dejan
--

CREATE TABLE booking.hotel (
    id bigint NOT NULL,
    hotel_description character varying(255) NOT NULL,
    hotel_location character varying(100) NOT NULL,
    hotel_name character varying(100) NOT NULL,
    hotel_image oid
);


ALTER TABLE booking.hotel OWNER TO dejan;

--
-- Name: hotel_id_seq; Type: SEQUENCE; Schema: booking; Owner: dejan
--

CREATE SEQUENCE booking.hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE booking.hotel_id_seq OWNER TO dejan;

--
-- Name: hotel_id_seq; Type: SEQUENCE OWNED BY; Schema: booking; Owner: dejan
--

ALTER SEQUENCE booking.hotel_id_seq OWNED BY booking.hotel.id;


--
-- Name: role; Type: TABLE; Schema: booking; Owner: dejan
--

CREATE TABLE booking.role (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE booking.role OWNER TO dejan;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: booking; Owner: dejan
--

CREATE SEQUENCE booking.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE booking.role_id_seq OWNER TO dejan;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: booking; Owner: dejan
--

ALTER SEQUENCE booking.role_id_seq OWNED BY booking.role.id;


--
-- Name: room_type; Type: TABLE; Schema: booking; Owner: dejan
--

CREATE TABLE booking.room_type (
    id bigint NOT NULL,
    check_in time without time zone NOT NULL,
    check_out time without time zone NOT NULL,
    max_persons integer NOT NULL,
    price real NOT NULL,
    room_type_name character varying(255) NOT NULL,
    vat real NOT NULL
);


ALTER TABLE booking.room_type OWNER TO dejan;

--
-- Name: room_type_id_seq; Type: SEQUENCE; Schema: booking; Owner: dejan
--

CREATE SEQUENCE booking.room_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE booking.room_type_id_seq OWNER TO dejan;

--
-- Name: room_type_id_seq; Type: SEQUENCE OWNED BY; Schema: booking; Owner: dejan
--

ALTER SEQUENCE booking.room_type_id_seq OWNED BY booking.room_type.id;


--
-- Name: app_user id; Type: DEFAULT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.app_user ALTER COLUMN id SET DEFAULT nextval('booking.app_user_id_seq'::regclass);


--
-- Name: booking id; Type: DEFAULT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.booking ALTER COLUMN id SET DEFAULT nextval('booking.booking_id_seq'::regclass);


--
-- Name: city id; Type: DEFAULT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.city ALTER COLUMN id SET DEFAULT nextval('booking.city_id_seq'::regclass);


--
-- Name: country id; Type: DEFAULT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.country ALTER COLUMN id SET DEFAULT nextval('booking.country_id_seq'::regclass);


--
-- Name: hotel id; Type: DEFAULT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.hotel ALTER COLUMN id SET DEFAULT nextval('booking.hotel_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.role ALTER COLUMN id SET DEFAULT nextval('booking.role_id_seq'::regclass);


--
-- Name: room_type id; Type: DEFAULT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.room_type ALTER COLUMN id SET DEFAULT nextval('booking.room_type_id_seq'::regclass);


--
-- Data for Name: app_user; Type: TABLE DATA; Schema: booking; Owner: dejan
--

COPY booking.app_user (id, address, email, name, password, user_name) FROM stdin;
1	Sydney	dejan@yahoo.com	Dejan Cvetkovski	$2a$10$.ybpTVJuMGaoS1KNdLt5UeYwHEMJFQdS9Da4C6uMQ9/MLaaVZgoiK	dejan
\.


--
-- Data for Name: app_user_roles; Type: TABLE DATA; Schema: booking; Owner: dejan
--

COPY booking.app_user_roles (app_user_id, role_id) FROM stdin;
1	1
1	2
\.


--
-- Data for Name: booking; Type: TABLE DATA; Schema: booking; Owner: dejan
--

COPY booking.booking (id, adults, check_in, check_out, children, city_id, country_id, customer_name, dob, extra_bed, gender, guest1age, guest1gender, guest1name, guest2age, guest2gender, guest2name, hotel_id, is_expanded, phone, room_type_id, status) FROM stdin;
\.


--
-- Data for Name: booking_dine_in; Type: TABLE DATA; Schema: booking; Owner: dejan
--

COPY booking.booking_dine_in (owner_id, dine_in) FROM stdin;
\.


--
-- Data for Name: booking_foods; Type: TABLE DATA; Schema: booking; Owner: dejan
--

COPY booking.booking_foods (owner_id, food) FROM stdin;
\.


--
-- Data for Name: city; Type: TABLE DATA; Schema: booking; Owner: dejan
--

COPY booking.city (id, city_name) FROM stdin;
1	Abu Dhabi
2	Amsterdam
3	Berlin
4	Chicago
5	Doha
6	Dubai
7	Istanbul
8	Las Vegas
9	London
10	Los Angeles
11	Moscow
12	Mumbai
13	New York
14	Paris
15	San Francisco
16	Seoul
17	Singapore
18	Sydney
19	Tokyo
20	Toronto
21	Washington
\.


--
-- Data for Name: country; Type: TABLE DATA; Schema: booking; Owner: dejan
--

COPY booking.country (id, country_name) FROM stdin;
1	Afghanistan
2	Australia
3	Egypt
4	Ethiopia
5	Georgia
6	Germany
7	Finland
8	France
9	India
10	Iraq
11	Italy
12	United Kingdom
13	United States
\.


--
-- Data for Name: hotel; Type: TABLE DATA; Schema: booking; Owner: dejan
--

COPY booking.hotel (id, hotel_description, hotel_location, hotel_name, hotel_image) FROM stdin;
3	sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est.	Garmsby	Tranquil Tavern	\N
4	Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex.	Kingcardine	Motel Merry	\N
5	But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born.	Perthlochry	Mirth Motel	\N
1	Lorem ipsum dolor sit amet consectetur adipisicing elit. Provident laboriosam ipsam reprehenderit.	Cromerth	Serene Stays	19717
2	Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.	Wingston	Leisure Inn	19728
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: booking; Owner: dejan
--

COPY booking.role (id, name) FROM stdin;
1	ROLE_USER
2	ROLE_ADMIN
\.


--
-- Data for Name: room_type; Type: TABLE DATA; Schema: booking; Owner: dejan
--

COPY booking.room_type (id, check_in, check_out, max_persons, price, room_type_name, vat) FROM stdin;
1	12:00:00	11:30:00	2	100	Standard Double Room	0.1
2	12:00:00	11:30:00	2	120	Deluxe Double Room	0.1
3	12:00:00	11:30:00	2	150	Business Class Double Room	0.1
4	12:00:00	11:30:00	1	60	Standard Single Room	0.1
5	12:00:00	11:30:00	1	75	Deluxe Single Room	0.1
6	12:00:00	11:30:00	1	90	Business Class Single Room	0.1
\.


--
-- Name: app_user_id_seq; Type: SEQUENCE SET; Schema: booking; Owner: dejan
--

SELECT pg_catalog.setval('booking.app_user_id_seq', 1, true);


--
-- Name: booking_id_seq; Type: SEQUENCE SET; Schema: booking; Owner: dejan
--

SELECT pg_catalog.setval('booking.booking_id_seq', 1, false);


--
-- Name: city_id_seq; Type: SEQUENCE SET; Schema: booking; Owner: dejan
--

SELECT pg_catalog.setval('booking.city_id_seq', 21, true);


--
-- Name: country_id_seq; Type: SEQUENCE SET; Schema: booking; Owner: dejan
--

SELECT pg_catalog.setval('booking.country_id_seq', 13, true);


--
-- Name: hotel_id_seq; Type: SEQUENCE SET; Schema: booking; Owner: dejan
--

SELECT pg_catalog.setval('booking.hotel_id_seq', 5, true);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: booking; Owner: dejan
--

SELECT pg_catalog.setval('booking.role_id_seq', 2, true);


--
-- Name: room_type_id_seq; Type: SEQUENCE SET; Schema: booking; Owner: dejan
--

SELECT pg_catalog.setval('booking.room_type_id_seq', 6, true);


--
-- Name: app_user app_user_pkey; Type: CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.app_user
    ADD CONSTRAINT app_user_pkey PRIMARY KEY (id);


--
-- Name: app_user_roles app_user_roles_pkey; Type: CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.app_user_roles
    ADD CONSTRAINT app_user_roles_pkey PRIMARY KEY (app_user_id, role_id);


--
-- Name: booking booking_pkey; Type: CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.booking
    ADD CONSTRAINT booking_pkey PRIMARY KEY (id);


--
-- Name: city city_pkey; Type: CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);


--
-- Name: country country_pkey; Type: CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);


--
-- Name: hotel hotel_pkey; Type: CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: room_type room_type_pkey; Type: CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.room_type
    ADD CONSTRAINT room_type_pkey PRIMARY KEY (id);


--
-- Name: city uk_djnk44fptegbsu6drhc9xvlfj; Type: CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.city
    ADD CONSTRAINT uk_djnk44fptegbsu6drhc9xvlfj UNIQUE (city_name);


--
-- Name: country uk_qrkn270121aljmucrdbnmd35p; Type: CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.country
    ADD CONSTRAINT uk_qrkn270121aljmucrdbnmd35p UNIQUE (country_name);


--
-- Name: app_user_roles fk12tauuariiusdbesvpbww106k; Type: FK CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.app_user_roles
    ADD CONSTRAINT fk12tauuariiusdbesvpbww106k FOREIGN KEY (role_id) REFERENCES booking.role(id);


--
-- Name: booking fk7eqvjwxkca3cc4vnj4y9cprkq; Type: FK CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.booking
    ADD CONSTRAINT fk7eqvjwxkca3cc4vnj4y9cprkq FOREIGN KEY (room_type_id) REFERENCES booking.room_type(id);


--
-- Name: booking fkduvyvro3lbicslryt5t3ohb7h; Type: FK CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.booking
    ADD CONSTRAINT fkduvyvro3lbicslryt5t3ohb7h FOREIGN KEY (country_id) REFERENCES booking.country(id);


--
-- Name: booking fkfihkjigldanoyfknph3arvagl; Type: FK CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.booking
    ADD CONSTRAINT fkfihkjigldanoyfknph3arvagl FOREIGN KEY (city_id) REFERENCES booking.city(id);


--
-- Name: booking fkhacdq9bfa3r9xdimovsnonbyi; Type: FK CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.booking
    ADD CONSTRAINT fkhacdq9bfa3r9xdimovsnonbyi FOREIGN KEY (hotel_id) REFERENCES booking.hotel(id);


--
-- Name: app_user_roles fkkwxexnudtp5gmt82j0qtytnoe; Type: FK CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.app_user_roles
    ADD CONSTRAINT fkkwxexnudtp5gmt82j0qtytnoe FOREIGN KEY (app_user_id) REFERENCES booking.app_user(id);


--
-- Name: booking_foods fknmkf3mmy7hc0n737ygcvojjn1; Type: FK CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.booking_foods
    ADD CONSTRAINT fknmkf3mmy7hc0n737ygcvojjn1 FOREIGN KEY (owner_id) REFERENCES booking.booking(id);


--
-- Name: booking_dine_in fks3w8eiuic21i40hqjlpsedbss; Type: FK CONSTRAINT; Schema: booking; Owner: dejan
--

ALTER TABLE ONLY booking.booking_dine_in
    ADD CONSTRAINT fks3w8eiuic21i40hqjlpsedbss FOREIGN KEY (owner_id) REFERENCES booking.booking(id);


--
-- PostgreSQL database dump complete
--