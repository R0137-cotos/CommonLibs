insert into cas_license_basic_info (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , customer_id
  , trend_user_id
  , mvb_account
  , license_status
  , mom_company_id
  , pic_name_sei
  , pic_name_mei
  , pic_mail_address
  , company_name
  , prefectures
  , municipality
  , initial_password
) values (
  9999
  , '2020/10/28 17:00:00'
  , 'COTOS'
  , '2020/10/28 17:00:00'
  , 'COTOS'
  , 1
  , 'customer_id'
  , 'trend_user_id'
  , 'mvb_account'
  , 0
  , 'mom_company_id'
  , 'pic_name_sei'
  , 'pic_name_mei'
  , 'pic_mail_address'
  , 'company_name'
  , 'prefectures'
  , 'municipality'
  , 'initial_password'
);
insert into cas_license_detail_info (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , cas_license_basic_info_id
  , subscription_id
  , service_plan_id
  , license_status
  , license_term_start
  , license_term_end
  , quantity
)　values (
  9999
  , '2020/10/28 17:00:00'
  , 'COTOS'
  , '2020/10/28 17:00:00'
  , 'COTOS'
  , 1
  , 9999
  , 'subscription_id'
  , 'service_plan_id'
  , 1
  , '2020/11/01'
  , '2021/10/31'
  , 1
);
insert into cas_license_management_info (
  id
  , created_at
  , created_user_id
  , updated_at
  , updated_user_id
  , version
  , cas_license_basic_info_id
  , contract_id
  , rj_manage_number
  , contract_number
  , contract_branch_number
  , last_contract_sync_date
) values (
  9999
  , '2020/10/28 17:00:00'
  , 'COTOS'
  , '2020/10/28 17:00:00'
  , 'COTOS'
  , 1
  , 9999
  , 1
  , 'rj_manage_number'
  , 'contract_number'
  , 1
  , '2020/10/28'
);