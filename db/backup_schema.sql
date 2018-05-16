PGDMP     ,                    v            postgres    10.3    10.3 �               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    4            �            1259    16460 	   album_seq    SEQUENCE     r   CREATE SEQUENCE public.album_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
     DROP SEQUENCE public.album_seq;
       public       postgres    false    4            �            1259    16462    album    TABLE     �   CREATE TABLE public.album (
    id integer DEFAULT nextval('public.album_seq'::regclass) NOT NULL,
    label_id integer NOT NULL,
    singer_id integer NOT NULL,
    name character varying(45) NOT NULL,
    release date NOT NULL
);
    DROP TABLE public.album;
       public         postgres    false    212    4            �            1259    16476    album_genres    TABLE     c   CREATE TABLE public.album_genres (
    album_id integer NOT NULL,
    genre_id integer NOT NULL
);
     DROP TABLE public.album_genres;
       public         postgres    false    4            �            1259    16487    album_pic_seq    SEQUENCE     v   CREATE SEQUENCE public.album_pic_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.album_pic_seq;
       public       postgres    false    4            �            1259    16489 	   album_pic    TABLE     �   CREATE TABLE public.album_pic (
    id integer DEFAULT nextval('public.album_pic_seq'::regclass) NOT NULL,
    album_id integer NOT NULL
);
    DROP TABLE public.album_pic;
       public         postgres    false    219    4            �            1259    16511    carrier_pic_seq    SEQUENCE     x   CREATE SEQUENCE public.carrier_pic_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.carrier_pic_seq;
       public       postgres    false    4            �            1259    16495    carrier_seq    SEQUENCE     t   CREATE SEQUENCE public.carrier_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.carrier_seq;
       public       postgres    false    4            �            1259    16503    carrier_type_seq    SEQUENCE     y   CREATE SEQUENCE public.carrier_type_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.carrier_type_seq;
       public       postgres    false    4            �            1259    16468    composition_seq    SEQUENCE     x   CREATE SEQUENCE public.composition_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.composition_seq;
       public       postgres    false    4            �            1259    16470    composition    TABLE       CREATE TABLE public.composition (
    id integer DEFAULT nextval('public.composition_seq'::regclass) NOT NULL,
    album_id integer NOT NULL,
    album_pos integer NOT NULL,
    name character varying(45) NOT NULL,
    duration time without time zone NOT NULL
);
    DROP TABLE public.composition;
       public         postgres    false    214    4            �            1259    16538    current_basket    TABLE     �   CREATE TABLE public.current_basket (
    user_id integer NOT NULL,
    media_id integer NOT NULL,
    media_count integer DEFAULT 1 NOT NULL
);
 "   DROP TABLE public.current_basket;
       public         postgres    false    4            �            1259    16452 	   genre_seq    SEQUENCE     r   CREATE SEQUENCE public.genre_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
     DROP SEQUENCE public.genre_seq;
       public       postgres    false    4            �            1259    16454    genre    TABLE     �   CREATE TABLE public.genre (
    id integer DEFAULT nextval('public.genre_seq'::regclass) NOT NULL,
    name character varying(45) NOT NULL
);
    DROP TABLE public.genre;
       public         postgres    false    210    4            �            1259    16420 	   label_seq    SEQUENCE     r   CREATE SEQUENCE public.label_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
     DROP SEQUENCE public.label_seq;
       public       postgres    false    4            �            1259    16422    label    TABLE     �   CREATE TABLE public.label (
    id integer DEFAULT nextval('public.label_seq'::regclass) NOT NULL,
    manager_id integer NOT NULL,
    name character varying(45) NOT NULL,
    address character varying(45) NOT NULL
);
    DROP TABLE public.label;
       public         postgres    false    202    4            �            1259    16428    label_pic_seq    SEQUENCE     v   CREATE SEQUENCE public.label_pic_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.label_pic_seq;
       public       postgres    false    4            �            1259    16430 	   label_pic    TABLE     �   CREATE TABLE public.label_pic (
    id integer DEFAULT nextval('public.label_pic_seq'::regclass) NOT NULL,
    label_id integer NOT NULL
);
    DROP TABLE public.label_pic;
       public         postgres    false    204    4            �            1259    16497    media    TABLE     �   CREATE TABLE public.media (
    id integer DEFAULT nextval('public.carrier_seq'::regclass) NOT NULL,
    type_id integer NOT NULL,
    album_id integer NOT NULL,
    price integer NOT NULL
);
    DROP TABLE public.media;
       public         postgres    false    221    4            �            1259    16535    media_order    TABLE     b   CREATE TABLE public.media_order (
    media_id integer NOT NULL,
    order_id integer NOT NULL
);
    DROP TABLE public.media_order;
       public         postgres    false    4            �            1259    16513 	   media_pic    TABLE     �   CREATE TABLE public.media_pic (
    id integer DEFAULT nextval('public.carrier_pic_seq'::regclass) NOT NULL,
    media_id integer NOT NULL
);
    DROP TABLE public.media_pic;
       public         postgres    false    225    4            �            1259    16505 
   media_type    TABLE     �   CREATE TABLE public.media_type (
    id integer DEFAULT nextval('public.carrier_type_seq'::regclass) NOT NULL,
    name character varying(45) NOT NULL
);
    DROP TABLE public.media_type;
       public         postgres    false    223    4            �            1259    16519 	   order_seq    SEQUENCE     r   CREATE SEQUENCE public.order_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
     DROP SEQUENCE public.order_seq;
       public       postgres    false    4            �            1259    16521    order    TABLE     �   CREATE TABLE public."order" (
    id integer DEFAULT nextval('public.order_seq'::regclass) NOT NULL,
    status_id integer NOT NULL,
    address_id integer NOT NULL
);
    DROP TABLE public."order";
       public         postgres    false    227    4            �            1259    16527    order_status_seq    SEQUENCE     y   CREATE SEQUENCE public.order_status_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.order_status_seq;
       public       postgres    false    4            �            1259    16529    order_status    TABLE     �   CREATE TABLE public.order_status (
    id integer DEFAULT nextval('public.order_status_seq'::regclass) NOT NULL,
    name character varying(45) NOT NULL,
    code character varying(45) NOT NULL,
    description character varying(90)
);
     DROP TABLE public.order_status;
       public         postgres    false    229    4            �            1259    16444 
   singer_seq    SEQUENCE     s   CREATE SEQUENCE public.singer_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.singer_seq;
       public       postgres    false    4            �            1259    16446    singer    TABLE     �   CREATE TABLE public.singer (
    id integer DEFAULT nextval('public.singer_seq'::regclass) NOT NULL,
    name character varying(45) NOT NULL
);
    DROP TABLE public.singer;
       public         postgres    false    208    4            �            1259    16479    singer_pic_seq    SEQUENCE     w   CREATE SEQUENCE public.singer_pic_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.singer_pic_seq;
       public       postgres    false    4            �            1259    16481 
   singer_pic    TABLE     �   CREATE TABLE public.singer_pic (
    id integer DEFAULT nextval('public.singer_pic_seq'::regclass) NOT NULL,
    singer_id integer NOT NULL
);
    DROP TABLE public.singer_pic;
       public         postgres    false    217    4            �            1259    16409    user_seq    SEQUENCE     q   CREATE SEQUENCE public.user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    DROP SEQUENCE public.user_seq;
       public       postgres    false    4            �            1259    16411    user    TABLE     ;  CREATE TABLE public."user" (
    id integer DEFAULT nextval('public.user_seq'::regclass) NOT NULL,
    login character varying(45) NOT NULL,
    password character varying(90) NOT NULL,
    name character varying(45) NOT NULL,
    surname character varying(45) NOT NULL,
    phone character varying(16) NOT NULL
);
    DROP TABLE public."user";
       public         postgres    false    199    4            �            1259    16676    user_address    TABLE     �   CREATE TABLE public.user_address (
    id integer NOT NULL,
    user_id integer NOT NULL,
    address character varying(90) NOT NULL
);
     DROP TABLE public.user_address;
       public         postgres    false    4            �            1259    16674    user_address_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_address_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.user_address_id_seq;
       public       postgres    false    234    4                       0    0    user_address_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.user_address_id_seq OWNED BY public.user_address.id;
            public       postgres    false    233            �            1259    16436    user_pic_seq    SEQUENCE     u   CREATE SEQUENCE public.user_pic_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.user_pic_seq;
       public       postgres    false    4            �            1259    16438    user_pic    TABLE     �   CREATE TABLE public.user_pic (
    id integer DEFAULT nextval('public.user_pic_seq'::regclass) NOT NULL,
    user_id integer NOT NULL
);
    DROP TABLE public.user_pic;
       public         postgres    false    206    4            �            1259    16401    user_role_seq    SEQUENCE     v   CREATE SEQUENCE public.user_role_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.user_role_seq;
       public       postgres    false    4            �            1259    16403 	   user_role    TABLE     �   CREATE TABLE public.user_role (
    id integer DEFAULT nextval('public.user_role_seq'::regclass) NOT NULL,
    code character varying(45) NOT NULL,
    description character varying(90)
);
    DROP TABLE public.user_role;
       public         postgres    false    197    4            �            1259    16417 
   user_roles    TABLE     _   CREATE TABLE public.user_roles (
    user_id integer NOT NULL,
    role_id integer NOT NULL
);
    DROP TABLE public.user_roles;
       public         postgres    false    4                        2604    16679    user_address id    DEFAULT     r   ALTER TABLE ONLY public.user_address ALTER COLUMN id SET DEFAULT nextval('public.user_address_id_seq'::regclass);
 >   ALTER TABLE public.user_address ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    234    233    234            �          0    16462    album 
   TABLE DATA               G   COPY public.album (id, label_id, singer_id, name, release) FROM stdin;
    public       postgres    false    213   s�       �          0    16476    album_genres 
   TABLE DATA               :   COPY public.album_genres (album_id, genre_id) FROM stdin;
    public       postgres    false    216   ��       �          0    16489 	   album_pic 
   TABLE DATA               1   COPY public.album_pic (id, album_id) FROM stdin;
    public       postgres    false    220   ��       �          0    16470    composition 
   TABLE DATA               N   COPY public.composition (id, album_id, album_pos, name, duration) FROM stdin;
    public       postgres    false    215   ʓ                 0    16538    current_basket 
   TABLE DATA               H   COPY public.current_basket (user_id, media_id, media_count) FROM stdin;
    public       postgres    false    232   �       �          0    16454    genre 
   TABLE DATA               )   COPY public.genre (id, name) FROM stdin;
    public       postgres    false    211   �       �          0    16422    label 
   TABLE DATA               >   COPY public.label (id, manager_id, name, address) FROM stdin;
    public       postgres    false    203   !�       �          0    16430 	   label_pic 
   TABLE DATA               1   COPY public.label_pic (id, label_id) FROM stdin;
    public       postgres    false    205   >�       �          0    16497    media 
   TABLE DATA               =   COPY public.media (id, type_id, album_id, price) FROM stdin;
    public       postgres    false    222   [�                 0    16535    media_order 
   TABLE DATA               9   COPY public.media_order (media_id, order_id) FROM stdin;
    public       postgres    false    231   x�       �          0    16513 	   media_pic 
   TABLE DATA               1   COPY public.media_pic (id, media_id) FROM stdin;
    public       postgres    false    226   ��       �          0    16505 
   media_type 
   TABLE DATA               .   COPY public.media_type (id, name) FROM stdin;
    public       postgres    false    224   ��       �          0    16521    order 
   TABLE DATA               <   COPY public."order" (id, status_id, address_id) FROM stdin;
    public       postgres    false    228   ϔ                 0    16529    order_status 
   TABLE DATA               C   COPY public.order_status (id, name, code, description) FROM stdin;
    public       postgres    false    230   �       �          0    16446    singer 
   TABLE DATA               *   COPY public.singer (id, name) FROM stdin;
    public       postgres    false    209   	�       �          0    16481 
   singer_pic 
   TABLE DATA               3   COPY public.singer_pic (id, singer_id) FROM stdin;
    public       postgres    false    218   &�       �          0    16411    user 
   TABLE DATA               K   COPY public."user" (id, login, password, name, surname, phone) FROM stdin;
    public       postgres    false    200   C�                 0    16676    user_address 
   TABLE DATA               <   COPY public.user_address (id, user_id, address) FROM stdin;
    public       postgres    false    234   `�       �          0    16438    user_pic 
   TABLE DATA               /   COPY public.user_pic (id, user_id) FROM stdin;
    public       postgres    false    207   }�       �          0    16403 	   user_role 
   TABLE DATA               :   COPY public.user_role (id, code, description) FROM stdin;
    public       postgres    false    198   ��       �          0    16417 
   user_roles 
   TABLE DATA               6   COPY public.user_roles (user_id, role_id) FROM stdin;
    public       postgres    false    201   ��                  0    0    album_pic_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.album_pic_seq', 1, false);
            public       postgres    false    219                       0    0 	   album_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('public.album_seq', 1, false);
            public       postgres    false    212                       0    0    carrier_pic_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.carrier_pic_seq', 1, false);
            public       postgres    false    225                       0    0    carrier_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.carrier_seq', 1, false);
            public       postgres    false    221                       0    0    carrier_type_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.carrier_type_seq', 1, false);
            public       postgres    false    223                       0    0    composition_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.composition_seq', 1, false);
            public       postgres    false    214                       0    0 	   genre_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('public.genre_seq', 1, false);
            public       postgres    false    210                       0    0    label_pic_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.label_pic_seq', 1, false);
            public       postgres    false    204                       0    0 	   label_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('public.label_seq', 1, false);
            public       postgres    false    202                       0    0 	   order_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('public.order_seq', 1, false);
            public       postgres    false    227                       0    0    order_status_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.order_status_seq', 1, false);
            public       postgres    false    229                       0    0    singer_pic_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.singer_pic_seq', 1, false);
            public       postgres    false    217                       0    0 
   singer_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.singer_seq', 1, false);
            public       postgres    false    208                       0    0    user_address_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.user_address_id_seq', 1, false);
            public       postgres    false    233                       0    0    user_pic_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.user_pic_seq', 1, false);
            public       postgres    false    206                       0    0    user_role_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.user_role_seq', 1, false);
            public       postgres    false    197                       0    0    user_seq    SEQUENCE SET     7   SELECT pg_catalog.setval('public.user_seq', 1, false);
            public       postgres    false    199            8           2606    16648 .   album_genres album_genres_album_id_genre_id_pk 
   CONSTRAINT     |   ALTER TABLE ONLY public.album_genres
    ADD CONSTRAINT album_genres_album_id_genre_id_pk PRIMARY KEY (album_id, genre_id);
 X   ALTER TABLE ONLY public.album_genres DROP CONSTRAINT album_genres_album_id_genre_id_pk;
       public         postgres    false    216    216            >           2606    16494    album_pic album_pic_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.album_pic
    ADD CONSTRAINT album_pic_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.album_pic DROP CONSTRAINT album_pic_pkey;
       public         postgres    false    220            4           2606    16467    album album_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.album
    ADD CONSTRAINT album_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.album DROP CONSTRAINT album_pkey;
       public         postgres    false    213            6           2606    16475    composition composition_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.composition
    ADD CONSTRAINT composition_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.composition DROP CONSTRAINT composition_pkey;
       public         postgres    false    215            M           2606    16651 1   current_basket current_basket_user_id_media_id_pk 
   CONSTRAINT     ~   ALTER TABLE ONLY public.current_basket
    ADD CONSTRAINT current_basket_user_id_media_id_pk PRIMARY KEY (user_id, media_id);
 [   ALTER TABLE ONLY public.current_basket DROP CONSTRAINT current_basket_user_id_media_id_pk;
       public         postgres    false    232    232            2           2606    16459    genre genre_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.genre
    ADD CONSTRAINT genre_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.genre DROP CONSTRAINT genre_pkey;
       public         postgres    false    211            +           2606    16435    label_pic label_pic_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.label_pic
    ADD CONSTRAINT label_pic_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.label_pic DROP CONSTRAINT label_pic_pkey;
       public         postgres    false    205            (           2606    16427    label label_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.label
    ADD CONSTRAINT label_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.label DROP CONSTRAINT label_pkey;
       public         postgres    false    203            K           2606    16658 ,   media_order media_order_media_id_order_id_pk 
   CONSTRAINT     z   ALTER TABLE ONLY public.media_order
    ADD CONSTRAINT media_order_media_id_order_id_pk PRIMARY KEY (media_id, order_id);
 V   ALTER TABLE ONLY public.media_order DROP CONSTRAINT media_order_media_id_order_id_pk;
       public         postgres    false    231    231            E           2606    16518    media_pic media_pic_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.media_pic
    ADD CONSTRAINT media_pic_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.media_pic DROP CONSTRAINT media_pic_pkey;
       public         postgres    false    226            @           2606    16502    media media_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.media
    ADD CONSTRAINT media_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.media DROP CONSTRAINT media_pkey;
       public         postgres    false    222            B           2606    16510    media_type media_type_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.media_type
    ADD CONSTRAINT media_type_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.media_type DROP CONSTRAINT media_type_pkey;
       public         postgres    false    224            G           2606    16526    order order_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_pkey;
       public         postgres    false    228            I           2606    16534    order_status order_status_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.order_status
    ADD CONSTRAINT order_status_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.order_status DROP CONSTRAINT order_status_pkey;
       public         postgres    false    230            :           2606    16486    singer_pic singer_pic_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.singer_pic
    ADD CONSTRAINT singer_pic_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.singer_pic DROP CONSTRAINT singer_pic_pkey;
       public         postgres    false    218            0           2606    16451    singer singer_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.singer
    ADD CONSTRAINT singer_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.singer DROP CONSTRAINT singer_pkey;
       public         postgres    false    209            P           2606    16681    user_address user_address_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.user_address
    ADD CONSTRAINT user_address_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.user_address DROP CONSTRAINT user_address_pkey;
       public         postgres    false    234            -           2606    16443    user_pic user_pic_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.user_pic
    ADD CONSTRAINT user_pic_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.user_pic DROP CONSTRAINT user_pic_pkey;
       public         postgres    false    207            $           2606    16416    user user_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public         postgres    false    200            "           2606    16408    user_role user_role_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.user_role DROP CONSTRAINT user_role_pkey;
       public         postgres    false    198            &           2606    16668 (   user_roles user_roles_user_id_role_id_pk 
   CONSTRAINT     t   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_user_id_role_id_pk PRIMARY KEY (user_id, role_id);
 R   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT user_roles_user_id_role_id_pk;
       public         postgres    false    201    201            <           1259    16649    album_pic_album_id_uindex    INDEX     Z   CREATE UNIQUE INDEX album_pic_album_id_uindex ON public.album_pic USING btree (album_id);
 -   DROP INDEX public.album_pic_album_id_uindex;
       public         postgres    false    220            )           1259    16659    label_pic_label_id_uindex    INDEX     Z   CREATE UNIQUE INDEX label_pic_label_id_uindex ON public.label_pic USING btree (label_id);
 -   DROP INDEX public.label_pic_label_id_uindex;
       public         postgres    false    205            C           1259    16660    media_pic_media_id_uindex    INDEX     Z   CREATE UNIQUE INDEX media_pic_media_id_uindex ON public.media_pic USING btree (media_id);
 -   DROP INDEX public.media_pic_media_id_uindex;
       public         postgres    false    226            ;           1259    16665    singer_pic_singer_id_uindex    INDEX     ^   CREATE UNIQUE INDEX singer_pic_singer_id_uindex ON public.singer_pic USING btree (singer_id);
 /   DROP INDEX public.singer_pic_singer_id_uindex;
       public         postgres    false    218            N           1259    16687    user_address_id_uindex    INDEX     T   CREATE UNIQUE INDEX user_address_id_uindex ON public.user_address USING btree (id);
 *   DROP INDEX public.user_address_id_uindex;
       public         postgres    false    234            .           1259    16666    user_pic_user_id_uindex    INDEX     V   CREATE UNIQUE INDEX user_pic_user_id_uindex ON public.user_pic USING btree (user_id);
 +   DROP INDEX public.user_pic_user_id_uindex;
       public         postgres    false    207            Y           2606    16581 '   album_genres album_genres_album_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.album_genres
    ADD CONSTRAINT album_genres_album_id_fkey FOREIGN KEY (album_id) REFERENCES public.album(id);
 Q   ALTER TABLE ONLY public.album_genres DROP CONSTRAINT album_genres_album_id_fkey;
       public       postgres    false    216    213    2868            Z           2606    16586 '   album_genres album_genres_genre_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.album_genres
    ADD CONSTRAINT album_genres_genre_id_fkey FOREIGN KEY (genre_id) REFERENCES public.genre(id);
 Q   ALTER TABLE ONLY public.album_genres DROP CONSTRAINT album_genres_genre_id_fkey;
       public       postgres    false    2866    211    216            V           2606    16566    album album_label_id_fkey    FK CONSTRAINT     y   ALTER TABLE ONLY public.album
    ADD CONSTRAINT album_label_id_fkey FOREIGN KEY (label_id) REFERENCES public.label(id);
 C   ALTER TABLE ONLY public.album DROP CONSTRAINT album_label_id_fkey;
       public       postgres    false    213    203    2856            \           2606    16596 !   album_pic album_pic_album_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.album_pic
    ADD CONSTRAINT album_pic_album_id_fkey FOREIGN KEY (album_id) REFERENCES public.album(id);
 K   ALTER TABLE ONLY public.album_pic DROP CONSTRAINT album_pic_album_id_fkey;
       public       postgres    false    220    213    2868            W           2606    16571    album album_singer_id_fkey    FK CONSTRAINT     |   ALTER TABLE ONLY public.album
    ADD CONSTRAINT album_singer_id_fkey FOREIGN KEY (singer_id) REFERENCES public.singer(id);
 D   ALTER TABLE ONLY public.album DROP CONSTRAINT album_singer_id_fkey;
       public       postgres    false    213    209    2864            X           2606    16576 %   composition composition_album_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.composition
    ADD CONSTRAINT composition_album_id_fkey FOREIGN KEY (album_id) REFERENCES public.album(id);
 O   ALTER TABLE ONLY public.composition DROP CONSTRAINT composition_album_id_fkey;
       public       postgres    false    215    213    2868            e           2606    16641 +   current_basket current_basket_media_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.current_basket
    ADD CONSTRAINT current_basket_media_id_fkey FOREIGN KEY (media_id) REFERENCES public.media(id);
 U   ALTER TABLE ONLY public.current_basket DROP CONSTRAINT current_basket_media_id_fkey;
       public       postgres    false    232    222    2880            d           2606    16636 *   current_basket current_basket_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.current_basket
    ADD CONSTRAINT current_basket_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(id);
 T   ALTER TABLE ONLY public.current_basket DROP CONSTRAINT current_basket_user_id_fkey;
       public       postgres    false    200    2852    232            S           2606    16551    label label_manager_id_fkey    FK CONSTRAINT     ~   ALTER TABLE ONLY public.label
    ADD CONSTRAINT label_manager_id_fkey FOREIGN KEY (manager_id) REFERENCES public."user"(id);
 E   ALTER TABLE ONLY public.label DROP CONSTRAINT label_manager_id_fkey;
       public       postgres    false    2852    203    200            T           2606    16556 !   label_pic label_pic_label_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.label_pic
    ADD CONSTRAINT label_pic_label_id_fkey FOREIGN KEY (label_id) REFERENCES public.label(id);
 K   ALTER TABLE ONLY public.label_pic DROP CONSTRAINT label_pic_label_id_fkey;
       public       postgres    false    2856    205    203            ^           2606    16652    media media_album_id_fk    FK CONSTRAINT     w   ALTER TABLE ONLY public.media
    ADD CONSTRAINT media_album_id_fk FOREIGN KEY (album_id) REFERENCES public.album(id);
 A   ALTER TABLE ONLY public.media DROP CONSTRAINT media_album_id_fk;
       public       postgres    false    213    2868    222            b           2606    16626 %   media_order media_order_media_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.media_order
    ADD CONSTRAINT media_order_media_id_fkey FOREIGN KEY (media_id) REFERENCES public.media(id);
 O   ALTER TABLE ONLY public.media_order DROP CONSTRAINT media_order_media_id_fkey;
       public       postgres    false    231    222    2880            c           2606    16631 %   media_order media_order_order_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.media_order
    ADD CONSTRAINT media_order_order_id_fkey FOREIGN KEY (order_id) REFERENCES public."order"(id);
 O   ALTER TABLE ONLY public.media_order DROP CONSTRAINT media_order_order_id_fkey;
       public       postgres    false    228    231    2887            _           2606    16611 !   media_pic media_pic_media_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.media_pic
    ADD CONSTRAINT media_pic_media_id_fkey FOREIGN KEY (media_id) REFERENCES public.media(id);
 K   ALTER TABLE ONLY public.media_pic DROP CONSTRAINT media_pic_media_id_fkey;
       public       postgres    false    222    226    2880            ]           2606    16606    media media_type_id_fkey    FK CONSTRAINT     |   ALTER TABLE ONLY public.media
    ADD CONSTRAINT media_type_id_fkey FOREIGN KEY (type_id) REFERENCES public.media_type(id);
 B   ALTER TABLE ONLY public.media DROP CONSTRAINT media_type_id_fkey;
       public       postgres    false    222    224    2882            `           2606    16621    order order_status_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_status_id_fkey FOREIGN KEY (status_id) REFERENCES public.order_status(id);
 F   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_status_id_fkey;
       public       postgres    false    228    230    2889            a           2606    16688    order order_user_address_id_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_user_address_id_fk FOREIGN KEY (address_id) REFERENCES public.user_address(id);
 J   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_user_address_id_fk;
       public       postgres    false    228    234    2896            [           2606    16591 $   singer_pic singer_pic_singer_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.singer_pic
    ADD CONSTRAINT singer_pic_singer_id_fkey FOREIGN KEY (singer_id) REFERENCES public.singer(id);
 N   ALTER TABLE ONLY public.singer_pic DROP CONSTRAINT singer_pic_singer_id_fkey;
       public       postgres    false    2864    218    209            f           2606    16682 $   user_address user_address_user_id_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_address
    ADD CONSTRAINT user_address_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);
 N   ALTER TABLE ONLY public.user_address DROP CONSTRAINT user_address_user_id_fk;
       public       postgres    false    234    2852    200            U           2606    16561    user_pic user_pic_user_id_fkey    FK CONSTRAINT     ~   ALTER TABLE ONLY public.user_pic
    ADD CONSTRAINT user_pic_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(id);
 H   ALTER TABLE ONLY public.user_pic DROP CONSTRAINT user_pic_user_id_fkey;
       public       postgres    false    207    200    2852            R           2606    16546 "   user_roles user_roles_role_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.user_role(id);
 L   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT user_roles_role_id_fkey;
       public       postgres    false    2850    198    201            Q           2606    16541 "   user_roles user_roles_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(id);
 L   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT user_roles_user_id_fkey;
       public       postgres    false    201    200    2852            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �            x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �            x������ � �      �      x������ � �      �      x������ � �      �      x������ � �            x������ � �      �      x������ � �      �      x������ � �      �      x������ � �            x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     