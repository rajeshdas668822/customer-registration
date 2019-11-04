create table "public"."cust_address" (
       "address_id" varchar(255) not null,
        "city" varchar(255),
        "country" varchar(255),
        "pincode" varchar(255),
        "state" varchar(255),
        "unit" varchar(255),
        primary key ("address_id")
    )

create table "public"."cust_details" (
       "customer_id" varchar(255) not null,
        "customer_ref" varchar(128) not null,
        "email" varchar(255),
        "first_name" varchar(255),
        "home_phone" varchar(255),
        "last_name" varchar(255),
        "mobile" varchar(255),
        primary key ("customer_id")
 )

create table "public"."cust_policy" (
       "policy_id" int4 not null,
        "customer_ref" varchar(255),
        "effective_date" date,
        "maturity_date" date,
        "payment_mode" varchar(255),
        "policy_ref" varchar(128) not null,
        "policy_status" varchar(255),
        "policy_term" int4,
        "premium_amount" float8,
        "premium_payment_term" int4,
        "product_type" varchar(255),
        "sumassured" float8,
        primary key ("policy_id")
)

create table "public"."cust_policy_coverage"(
       "coverage_id" int4 not null,
        "coverage_end_date" date,
        "coverage_start_date" date,
        "extra_premium" float8,
        "policy_ref" varchar(255),
        "rider_name" varchar(255),
        "sum_assured" float8,
        "term" int4,
        primary key ("coverage_id")
  )


alter table if exists "public"."cust_details"
    add constraint UK_cust_details unique ("customer_ref")

alter table if exists "public"."cust_policy"
  add constraint UK_policy unique ("policy_ref")