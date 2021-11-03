package com.fragma.dao;

import com.fragma.config.ConfigurationHelper;
import com.fragma.dto.MainDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.*;

@Repository
public class ReportDao {

    static Logger LOG = LoggerFactory.getLogger(com.fragma.dao.ReportDao.class);

    private final JdbcTemplate jdbcTemplate;
    private final ConfigurationHelper configurationHelper;
    int SLNo=0;
    int SLNoLrg=0;

    @Autowired
    public ReportDao(@Qualifier("hiveJdbcTemplate") JdbcTemplate jdbcTemplate, ConfigurationHelper configurationHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.configurationHelper = configurationHelper;

    }

    public void getData(MainDto mainDto){
        LOG.info("***** executing getData *****");
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                LOG.info("Query = "+ ConfigurationHelper.getQuery() );
                PreparedStatement ps = connection.prepareStatement(ConfigurationHelper.getQuery());

                return ps;
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                String accountNumber = resultSet.getString("account_number");
                String accountCurrency = resultSet.getString("account_currency");
                String accountClass = resultSet.getString("account_class");
                String accountTitle = resultSet.getString("account_title");
                String trnRefNo = resultSet.getString("trn_ref_no");

                String productCode = resultSet.getString("product_code");
                String branchCode = resultSet.getString("branch_code");
                String remitterAccountNo = resultSet.getString("remitter_account_no");
                String beneficiaryAccountNo = resultSet.getString("beneficiary_account_no");
                String instrumentNo = resultSet.getString("instrument_no");
                String ccy = resultSet.getString("ccy");

                Double amount = resultSet.getDouble("amount");

                String valueDate = resultSet.getString("value_date");
                String activationDate = resultSet.getString("activation_date");
                String status = resultSet.getString("status");
                String dated = resultSet.getString("dated");


                LOG.info("accountNumber:"+accountNumber+"accountCurrency:"+accountCurrency+"accountClass:"+accountClass+"accountTitle:"+accountTitle+"trnRefNo:"+trnRefNo+"productCode:"+productCode+"branchCode"+branchCode+"remitterAccountNo"+remitterAccountNo+"beneficiaryAccountNo"+beneficiaryAccountNo+"instrumentNo"+instrumentNo+"ccy"+ccy+"amount"+amount+"valueDate"+valueDate+"activationDate"+activationDate+"status"+status+"dated"+dated);



                mainDto.populateData(++SLNo,accountNumber,accountCurrency,accountClass,accountTitle,trnRefNo,productCode,branchCode,remitterAccountNo,beneficiaryAccountNo,instrumentNo,ccy,amount,valueDate,activationDate,status,dated);
            }
        });
    }

    public void getLRGData(MainDto mainDto){
        LOG.info("***** executing getData *****");
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                LOG.info("Query = "+ ConfigurationHelper.getLrgquery() );
                PreparedStatement ps = connection.prepareStatement(ConfigurationHelper.getLrgquery());

                return ps;
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                String accountNumber = resultSet.getString("account_number");
                String accountCurrency = resultSet.getString("account_currency");
                String accountClass = resultSet.getString("account_class");
                String accountTitle = resultSet.getString("account_title");
                String trnRefNo = resultSet.getString("trn_ref_no");

                String productCode = resultSet.getString("product_code");
                String branchCode = resultSet.getString("branch_code");
                String remitterAccountNo = resultSet.getString("remitter_account_no");
                String beneficiaryAccountNo = resultSet.getString("beneficiary_account_no");
                String instrumentNo = resultSet.getString("instrument_no");
                String ccy = resultSet.getString("ccy");

                Double amount = resultSet.getDouble("amount");

                String valueDate = resultSet.getString("value_date");
                String activationDate = resultSet.getString("activation_date");
                String status = resultSet.getString("status");
                String dated = resultSet.getString("dated");


                LOG.info("accountNumber:"+accountNumber+"accountCurrency:"+accountCurrency+"accountClass:"+accountClass+"accountTitle:"+accountTitle+"trnRefNo:"+trnRefNo+"productCode:"+productCode+"branchCode"+branchCode+"remitterAccountNo"+remitterAccountNo+"beneficiaryAccountNo"+beneficiaryAccountNo+"instrumentNo"+instrumentNo+"ccy"+ccy+"amount"+amount+"valueDate"+valueDate+"activationDate"+activationDate+"status"+status+"dated"+dated);



                mainDto.populateLRGData(++SLNoLrg,accountNumber,accountCurrency,accountClass,accountTitle,trnRefNo,productCode,branchCode,remitterAccountNo,beneficiaryAccountNo,instrumentNo,ccy,amount,valueDate,activationDate,status,dated);
            }
        });
    }

}
