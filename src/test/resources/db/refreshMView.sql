CREATE OR REPLACE PROCEDURE REFRESH_M_VIEW(MATERIALIZED_VIEW_NAME_LIST IN VARCHAR2)
IS
    -- 宣言の必要がないため、記載しない
BEGIN
    dbms_mview.refresh(list=>MATERIALIZED_VIEW_NAME_LIST
    , method=>'C',atomic_refresh=>FALSE
    );
END REFRESH_M_VIEW;