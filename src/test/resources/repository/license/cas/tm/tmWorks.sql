-- TM_連携管理
insert into tm_link_management (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , arrangement_work_id
  , tm_create_customer_request_work_id
  , tm_create_subscription_request_work_id
  , tm_update_subscription_request_work_id
  , tm_update_customer_request_work_id
  , tm_update_user_request_work_id
  , tm_suspend_subscription_request_work_id
  , mvb_account
  , contract_id
  , rj_manage_number
  , mom_company_id
  , mom_cust_id
  , company_name_kana
  , license_info_id
  , tm_transition_subscription_request_work_id
) values (
  10
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 1
  , 10
  , 10
  , 10
  , 10
  , 10
  , 10
  , 'mvb_account'
  , 1
  , 'rj_manage_number'
  , 'mom_company_id'
  , 'mom_cust_id'
  , 'company_name_kana'
  , 1
  , 10
);
insert into tm_link_management (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , arrangement_work_id
  , tm_create_customer_request_work_id
  , tm_create_subscription_request_work_id
  , tm_update_subscription_request_work_id
  , tm_update_customer_request_work_id
  , tm_update_user_request_work_id
  , tm_suspend_subscription_request_work_id
  , mvb_account
  , contract_id
  , rj_manage_number
  , mom_company_id
  , mom_cust_id
  , company_name_kana
  , license_info_id
  , tm_transition_subscription_request_work_id
) values (
  20
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 1
  , 20
  , 20
  , 20
  , 20
  , 20
  , 20
  , 'mvb_account'
  , 1
  , 'rj_manage_number'
  , 'mom_company_id'
  , 'mom_cust_id'
  , 'company_name_kana'
  , 1
  , 20
);
insert into tm_link_management (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , arrangement_work_id
  , tm_create_customer_request_work_id
  , tm_create_subscription_request_work_id
  , tm_update_subscription_request_work_id
  , tm_update_customer_request_work_id
  , tm_update_user_request_work_id
  , tm_suspend_subscription_request_work_id
  , mvb_account
  , contract_id
  , rj_manage_number
  , mom_company_id
  , mom_cust_id
  , company_name_kana
  , license_info_id
  , tm_transition_subscription_request_work_id
) values (
  30
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 1
  , 30
  , 30
  , 30
  , 30
  , 30
  , 30
  , 'mvb_account'
  , 1
  , 'rj_manage_number'
  , 'mom_company_id'
  , 'mom_cust_id'
  , 'company_name_kana'
  , 1
  , 30
);
-- TM_顧客情報作成リクエストWORK
insert into tm_create_customer_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , company_name
  , company_state
  , company_country
  , user_login_name
  , user_first_name
  , user_last_name
  , user_email
  , user_time_zone
  , user_language
  , company_city
) values (
  10
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '0'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'company_name'
  , 'company_state'
  , 'company_country'
  , 'user_login_name'
  , 'user_first_name'
  , 'user_last_name'
  , 'user_email'
  , 'user_time_zone'
  , 'user_language'
  , 'company_city'
);
insert into tm_create_customer_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , company_name
  , company_state
  , company_country
  , user_login_name
  , user_first_name
  , user_last_name
  , user_email
  , user_time_zone
  , user_language
  , company_city
) values (
  20
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '0'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'company_name'
  , 'company_state'
  , 'company_country'
  , 'user_login_name'
  , 'user_first_name'
  , 'user_last_name'
  , 'user_email'
  , 'user_time_zone'
  , 'user_language'
  , 'company_city'
);
insert into tm_create_customer_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , company_name
  , company_state
  , company_country
  , user_login_name
  , user_first_name
  , user_last_name
  , user_email
  , user_time_zone
  , user_language
  , company_city
) values (
  30
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '1'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'company_name'
  , 'company_state'
  , 'company_country'
  , 'user_login_name'
  , 'user_first_name'
  , 'user_last_name'
  , 'user_email'
  , 'user_time_zone'
  , 'user_language'
  , 'company_city'
);
-- TM_顧客情報作成レスポンスWORK
insert into  TM_CREATE_CUSTOMER_RESPONSE_WORK (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , company_id
  , user_id
  , user_login_name
  , user_password
  , user_resetpasswordurl
) values (
  10
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 10
  , 'http_status'
  , 'error_message'
  , '0'
  , 'company_id'
  , 'user_id'
  , 'user_login_name'
  , 'user_password'
  , 'user_resetpasswordurl'
);
insert into  TM_CREATE_CUSTOMER_RESPONSE_WORK (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , company_id
  , user_id
  , user_login_name
  , user_password
  , user_resetpasswordurl
) values (
  20
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 20
  , 'http_status'
  , 'error_message'
  , '0'
  , 'company_id'
  , 'user_id'
  , 'user_login_name'
  , 'user_password'
  , 'user_resetpasswordurl'
);
insert into  TM_CREATE_CUSTOMER_RESPONSE_WORK (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , company_id
  , user_id
  , user_login_name
  , user_password
  , user_resetpasswordurl
) values (
  30
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 30
  , 'http_status'
  , 'error_message'
  , '1'
  , 'company_id'
  , 'user_id'
  , 'user_login_name'
  , 'user_password'
  , 'user_resetpasswordurl'
);
-- TM_サブスクリプション作成リクエストWORK
insert into tm_create_subscription_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , customer_id
  , service_plan_id
  , units_per_license
  , license_start_date
) values (
  10
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '0'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'customer_id'
  , 'service_plan_id'
  , 'units_per_license'
  , '2020/11/02 17:00:00'
);
insert into tm_create_subscription_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , customer_id
  , service_plan_id
  , units_per_license
  , license_start_date
) values (
  20
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '0'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'customer_id'
  , 'service_plan_id'
  , 'units_per_license'
  , '2020/11/02 17:00:00'
);
insert into tm_create_subscription_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , customer_id
  , service_plan_id
  , units_per_license
  , license_start_date
) values (
  30
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '1'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'customer_id'
  , 'service_plan_id'
  , 'units_per_license'
  , '2020/11/02 17:00:00'
);
-- TM_サブスクリプション作成レスポンスWORK
insert into tm_create_subscription_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , subscription_id
  , product_name
  , service_url
  , product_id
  , versionlicence_version
  , ac_code
  , units
  , license_start_date
  , license_expiration_date
  , start_charge_date
) values (
  10
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 10
  , 'http_status'
  , 'error_message'
  , 0
  , 'subscription_id'
  , 'product_name'
  , 'service_url'
  , 'product_id'
  , 'versionlicence_version'
  , 'ac_code'
  , 'units'
  , '2020/11/02 17:00:00'
  , '2020/11/02 17:00:00'
  , '2020/11/02 17:00:00'
);
 insert into tm_create_subscription_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , subscription_id
  , product_name
  , service_url
  , product_id
  , versionlicence_version
  , ac_code
  , units
  , license_start_date
  , license_expiration_date
  , start_charge_date
) values (
  20
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 20
  , 'http_status'
  , 'error_message'
  , 0
  , 'subscription_id'
  , 'product_name'
  , 'service_url'
  , 'product_id'
  , 'versionlicence_version'
  , 'ac_code'
  , 'units'
  , '2020/11/02 17:00:00'
  , '2020/11/02 17:00:00'
  , '2020/11/02 17:00:00'
);
 insert into tm_create_subscription_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , subscription_id
  , product_name
  , service_url
  , product_id
  , versionlicence_version
  , ac_code
  , units
  , license_start_date
  , license_expiration_date
  , start_charge_date
) values (
  30
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 30
  , 'http_status'
  , 'error_message'
  , 1
  , 'subscription_id'
  , 'product_name'
  , 'service_url'
  , 'product_id'
  , 'versionlicence_version'
  , 'ac_code'
  , 'units'
  , '2020/11/02 17:00:00'
  , '2020/11/02 17:00:00'
  , '2020/11/02 17:00:00'
);
-- TM_サブスクリプション解約リクエストWORK
insert into tm_suspend_subscription_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , customer_id
  , subscription_id
  , license_expiration_date
) values(
  10
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '0'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'customer_id'
  , 'subscription_id'
  , '2020/11/02 17:00:00'
);
insert into tm_suspend_subscription_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , customer_id
  , subscription_id
  , license_expiration_date
) values(
  20
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '0'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'customer_id'
  , 'subscription_id'
  , '2020/11/02 17:00:00'
);
insert into tm_suspend_subscription_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , customer_id
  , subscription_id
  , license_expiration_date
) values(
  30
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '1'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'customer_id'
  , 'subscription_id'
  , '2020/11/02 17:00:00'
);
-- TM_サブスクリプション解約レスポンスWORK
insert into  tm_suspend_subscription_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
) values(
  10
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 10
  , 'http_status'
  , 'error_message'
  , 0
);
insert into  tm_suspend_subscription_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
) values(
  20
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 20
  , 'http_status'
  , 'error_message'
  , 0
);
insert into  tm_suspend_subscription_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
) values(
  30
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 30
  , 'http_status'
  , 'error_message'
  , 1
);
-- TM_会社情報更新リクエストWORK
insert into tm_update_customer_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , name
  , state
  , country
  , city
  , customer_id
) values(
  10
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '0'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'name'
  , 'state'
  , 'country'
  , 'city'
  , 'customer_id'
);
insert into tm_update_customer_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , name
  , state
  , country
  , city
  , customer_id
) values(
  20
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '0'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'name'
  , 'state'
  , 'country'
  , 'city'
  , 'customer_id'
);
insert into tm_update_customer_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , name
  , state
  , country
  , city
  , customer_id
) values(
  30
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '1'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'name'
  , 'state'
  , 'country'
  , 'city'
  , 'customer_id'
);
-- TM_会社情報更新レスポンスWORK
insert into tm_update_customer_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , name
  , state
  , country
  , city
  , address
  , postal_code
  , note
  , emergency_email
) values(
  10
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 10
  , 'http_status'
  , 'error_message'
  , '0'
  , 'name'
  , 'state'
  , 'country'
  , 'city'
  , 'address'
  , 'postal_code'
  , 'note'
  , 'emergency_email'
);
insert into tm_update_customer_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , name
  , state
  , country
  , city
  , address
  , postal_code
  , note
  , emergency_email
) values(
  20
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 20
  , 'http_status'
  , 'error_message'
  , '0'
  , 'name'
  , 'state'
  , 'country'
  , 'city'
  , 'address'
  , 'postal_code'
  , 'note'
  , 'emergency_email'
);
insert into tm_update_customer_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , name
  , state
  , country
  , city
  , address
  , postal_code
  , note
  , emergency_email
) values(
  30
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 30
  , 'http_status'
  , 'error_message'
  , '1'
  , 'name'
  , 'state'
  , 'country'
  , 'city'
  , 'address'
  , 'postal_code'
  , 'note'
  , 'emergency_email'
);
-- TM_サブスクリプション更新リクエストWORK
insert into tm_update_subscription_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , customer_id
  , subscription_id
  , units_per_license
) values(
  10
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '0'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'customer_id'
  , 'subscription_id'
  , 'units_per_license'
);
insert into tm_update_subscription_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , customer_id
  , subscription_id
  , units_per_license
) values(
  20
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '0'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'customer_id'
  , 'subscription_id'
  , 'units_per_license'
);
insert into tm_update_subscription_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , customer_id
  , subscription_id
  , units_per_license
) values(
  30
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '1'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'customer_id'
  , 'subscription_id'
  , 'units_per_license'
);
-- TM_サブスクリプション更新レスポンスWORK
insert into tm_update_subscription_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , subscription_id
  , name
  , enabled
  , is_auto_renewal
  , auto_renewal_month
  , expiration_notification
  , service_url
  , ac_code
  , product_id
  , license_start_date
  , license_expiration_date
  , start_charge_date
  , grace_period
  , units
  , license_enabled
) values(
  10
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 10
  , 'http_status'
  , 'error_message'
  , '0'
  , 'subscription_id'
  , 'name'
  , 0
  , 0
  , 'auto_renewal_month'
  , 'expiration_notification'
  , 'service_url'
  , 'ac_code'
  , 'product_id'
  , '2020/11/02'
  , '2020/11/02'
  , '2020/11/02'
  , 10
  , 10
  , 0
 );
 insert into tm_update_subscription_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , subscription_id
  , name
  , enabled
  , is_auto_renewal
  , auto_renewal_month
  , expiration_notification
  , service_url
  , ac_code
  , product_id
  , license_start_date
  , license_expiration_date
  , start_charge_date
  , grace_period
  , units
  , license_enabled
) values(
  20
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 20
  , 'http_status'
  , 'error_message'
  , '0'
  , 'subscription_id'
  , 'name'
  , 0
  , 0
  , 'auto_renewal_month'
  , 'expiration_notification'
  , 'service_url'
  , 'ac_code'
  , 'product_id'
  , '2020/11/02'
  , '2020/11/02'
  , '2020/11/02'
  , 10
  , 10
  , 0
 );
 insert into tm_update_subscription_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , subscription_id
  , name
  , enabled
  , is_auto_renewal
  , auto_renewal_month
  , expiration_notification
  , service_url
  , ac_code
  , product_id
  , license_start_date
  , license_expiration_date
  , start_charge_date
  , grace_period
  , units
  , license_enabled
) values(
  30
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 30
  , 'http_status'
  , 'error_message'
  , '1'
  , 'subscription_id'
  , 'name'
  , 0
  , 0
  , 'auto_renewal_month'
  , 'expiration_notification'
  , 'service_url'
  , 'ac_code'
  , 'product_id'
  , '2020/11/02'
  , '2020/11/02'
  , '2020/11/02'
  , 10
  , 10
  , 0
 );
 -- TM_ユーザーアカウント更新リクエストWORK
insert into tm_update_user_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , email
  , customer_id
  , user_id
) values(
  10
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '0'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'email'
  , 'customer_id'
  , 'user_id'
);
insert into tm_update_user_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , email
  , customer_id
  , user_id
) values(
  20
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '0'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'email'
  , 'customer_id'
  , 'user_id'
);
insert into tm_update_user_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , email
  , customer_id
  , user_id
) values(
  30
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '1'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'email'
  , 'customer_id'
  , 'user_id'
);
-- TM_ユーザーアカウント更新レスポンスWORK
insert into tm_update_user_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , user_id
  , first_name
  , last_name
  , email
  , time_zone
  , language
  , phone_area_code
  , phone_number
  , phone_extension
) values(
  10
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 10
  , 'http_status'
  , 'error_message'
  , '0'
  , 'user_id'
  , 'first_name'
  , 'last_name'
  , 'email'
  , 'time_zone'
  , 'language'
  , 'phone_area_code'
  , 'phone_number'
  , 'phone_extension'
);
insert into tm_update_user_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , user_id
  , first_name
  , last_name
  , email
  , time_zone
  , language
  , phone_area_code
  , phone_number
  , phone_extension
) values(
  20
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 20
  , 'http_status'
  , 'error_message'
  , '0'
  , 'user_id'
  , 'first_name'
  , 'last_name'
  , 'email'
  , 'time_zone'
  , 'language'
  , 'phone_area_code'
  , 'phone_number'
  , 'phone_extension'
);
insert into tm_update_user_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , user_id
  , first_name
  , last_name
  , email
  , time_zone
  , language
  , phone_area_code
  , phone_number
  , phone_extension
) values(
  30
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 30
  , 'http_status'
  , 'error_message'
  , '1'
  , 'user_id'
  , 'first_name'
  , 'last_name'
  , 'email'
  , 'time_zone'
  , 'language'
  , 'phone_area_code'
  , 'phone_number'
  , 'phone_extension'
);
-- TM_サブスクリプション乗換リクエストWORK
insert into tm_transition_subscription_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , customer_id
  , service_plan_id
  , units_per_license
  , transition_units_per_license
  , license_start_date
  , cross_grade_div
  , from_service_plan_id
  , from_license_end_date
  , from_suspend_flg
  , from_subscription_id
) values (
  10
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '0'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'customer_id'
  , 'service_plan_id'
  , 'units_per_license'
  , 'from_units_per_license'
  , '2020/11/02 17:00:00'
  , '1'
  , 'from_service_plan_id'
  , '2020/11/02 17:00:00'
  , 0
  , 'from_subscription_id'
);
insert into tm_transition_subscription_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , customer_id
  , service_plan_id
  , units_per_license
  , transition_units_per_license
  , license_start_date
  , cross_grade_div
  , from_service_plan_id
  , from_license_end_date
  , from_suspend_flg
  , from_subscription_id
) values (
  20
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '0'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'customer_id'
  , 'service_plan_id'
  , 'units_per_license'
  , 'from_units_per_license'
  , '2020/11/02 17:00:00'
  , '1'
  , 'from_service_plan_id'
  , '2020/11/02 17:00:00'
  , 0
  , 'from_subscription_id'
);
insert into tm_transition_subscription_request_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_status
  , request_time
  , url
  , http_header
  , http_body
  , customer_id
  , service_plan_id
  , units_per_license
  , transition_units_per_license
  , license_start_date
  , cross_grade_div
  , from_service_plan_id
  , from_license_end_date
  , from_suspend_flg
  , from_subscription_id
) values (
  30
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , '1'
  , '2020/11/02 17:00:00'
  , 'url'
  , 'http_header'
  , 'http_body'
  , 'customer_id'
  , 'service_plan_id'
  , 'units_per_license'
  , 'from_units_per_license'
  , '2020/11/02 17:00:00'
  , '1'
  , 'from_service_plan_id'
  , '2020/11/02 17:00:00'
  , 0
  , 'from_subscription_id'
);
-- TM_サブスクリプション乗換レスポンスWORK
insert into tm_transition_subscription_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , subscription_id
  , product_name
  , service_url
  , product_id
  , versionlicence_version
  , ac_code
  , units
  , license_start_date
  , license_expiration_date
  , start_charge_date
) values (
  10
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 10
  , 'http_status'
  , 'error_message'
  , 0
  , 'subscription_id'
  , 'product_name'
  , 'service_url'
  , 'product_id'
  , 'versionlicence_version'
  , 'ac_code'
  , 'units'
  , '2020/11/02 17:00:00'
  , '2020/11/02 17:00:00'
  , '2020/11/02 17:00:00'
);
 insert into tm_transition_subscription_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , subscription_id
  , product_name
  , service_url
  , product_id
  , versionlicence_version
  , ac_code
  , units
  , license_start_date
  , license_expiration_date
  , start_charge_date
) values (
  20
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 20
  , 'http_status'
  , 'error_message'
  , 0
  , 'subscription_id'
  , 'product_name'
  , 'service_url'
  , 'product_id'
  , 'versionlicence_version'
  , 'ac_code'
  , 'units'
  , '2020/11/02 17:00:00'
  , '2020/11/02 17:00:00'
  , '2020/11/02 17:00:00'
);
 insert into tm_transition_subscription_response_work (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , request_id
  , http_status
  , error_message
  , licence_mapped_status
  , subscription_id
  , product_name
  , service_url
  , product_id
  , versionlicence_version
  , ac_code
  , units
  , license_start_date
  , license_expiration_date
  , start_charge_date
) values (
  30
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , '2020/11/02 17:00:00'
  , 'COTOS'
  , 0
  , 30
  , 'http_status'
  , 'error_message'
  , 1
  , 'subscription_id'
  , 'product_name'
  , 'service_url'
  , 'product_id'
  , 'versionlicence_version'
  , 'ac_code'
  , 'units'
  , '2020/11/02 17:00:00'
  , '2020/11/02 17:00:00'
  , '2020/11/02 17:00:00'
);