SELECT
  1
FROM
 RESPONSIBLE_SALES
WHERE
 HANSH_CD IN (SELECT
                M150_HANSH.HANSH_CD
              FROM
                RESPONSIBLE_SALES M150_HANSH
              WHERE
                M150_HANSH.HAIKA_INTEGRATE_ID = :requesterIntegrateId)
 AND HAIKA_INTEGRATE_ID = :approverIntegrateId