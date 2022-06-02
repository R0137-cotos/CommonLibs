-- OrderBasicInfoRepository#findByNoErrorAndOrderNumber
INSERT INTO order_basic_info (id,version,orderer_number) values (10,0,'orderer_number-1');
INSERT INTO order_basic_info (id,version,orderer_number) values (11,0,'orderer_number-1');
INSERT INTO order_basic_info (id,version,orderer_number) values (12,0,'orderer_number-2');
INSERT INTO order_management_info (id,version,order_basic_info_id,contract_capture_status,contract_id,estimation_id) values (10,0,10,'1',10,10);
INSERT INTO order_management_info (id,version,order_basic_info_id,contract_capture_status,contract_id,estimation_id) values (11,0,11,'E',0,0);
INSERT INTO order_management_info (id,version,order_basic_info_id,contract_capture_status,contract_id,estimation_id) values (12,0,12,'1',12,12);
-- OrderBasicInfoRepository#findByNoErrorAndSubDomainForO365
INSERT INTO order_basic_info (id,version,orderer_type,product_type) values (20,0,'1','3');
INSERT INTO order_basic_info (id,version,orderer_type,product_type) values (21,0,'1','3');
INSERT INTO order_basic_info (id,version,orderer_type,product_type) values (22,0,'1','3');
INSERT INTO order_basic_info (id,version,orderer_type,product_type) values (23,0,'1','3');
INSERT INTO order_basic_info (id,version,orderer_type,product_type) values (24,0,'1','3');
INSERT INTO order_management_info (id,version,order_basic_info_id,contract_capture_status,contract_id,estimation_id) values (20,0,20,'0',0,0);
INSERT INTO order_management_info (id,version,order_basic_info_id,contract_capture_status,contract_id,estimation_id) values (21,0,21,'1',21,21);
INSERT INTO order_management_info (id,version,order_basic_info_id,contract_capture_status,contract_id,estimation_id) values (22,0,22,'1',22,22);
INSERT INTO order_management_info (id,version,order_basic_info_id,contract_capture_status,contract_id,estimation_id) values (23,0,23,'E',0,0);
INSERT INTO order_management_info (id,version,order_basic_info_id,contract_capture_status,contract_id,estimation_id) values (24,0,24,'1',24,24);
INSERT INTO order_service_inner_info (id,version,order_basic_info_id,item4) values (20,0,20,'subDomain-1');
INSERT INTO order_service_inner_info (id,version,order_basic_info_id,item4) values (21,0,21,'subDomain-1');
INSERT INTO order_service_inner_info (id,version,order_basic_info_id,item4) values (22,0,22,'subDomain-1');
INSERT INTO order_service_inner_info (id,version,order_basic_info_id,item4) values (23,0,23,'subDomain-1');
INSERT INTO order_service_inner_info (id,version,order_basic_info_id,item4) values (24,0,24,'subDomain-2');
INSERT INTO contract (id,version,lifecycle_status,contract_type,workflow_status,contract_number,contract_branch_number,account_sales_flg) values (21,0,'1','1','1','CC21',1,0);
INSERT INTO contract (id,version,lifecycle_status,contract_type,workflow_status,contract_number,contract_branch_number,account_sales_flg) values (22,0,'4','1','1','CC22',1,0);
INSERT INTO contract (id,version,lifecycle_status,contract_type,workflow_status,contract_number,contract_branch_number,account_sales_flg) values (24,0,'1','1','1','CC24',1,0);
-- OrderBasicInfoRepository#findByCancelContractIdForO365
INSERT INTO order_basic_info (id,version,orderer_type,product_type) values (30,0,'4','3');
INSERT INTO order_basic_info (id,version,orderer_type,product_type) values (31,0,'4','3');
INSERT INTO order_service_inner_info (id,version,order_basic_info_id,item5) values (30,0,30,'cancelId-1');
INSERT INTO order_service_inner_info (id,version,order_basic_info_id,item5) values (31,0,31,'cancelId-2');