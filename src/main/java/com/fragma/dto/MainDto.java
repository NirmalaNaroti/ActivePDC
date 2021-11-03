package com.fragma.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;

public class MainDto {

    static Logger LOG = LoggerFactory.getLogger(com.fragma.dto.MainDto.class);

    public Map<Integer, FTG> ftgMap = new HashMap<>();
    public Map<Integer, LRG> lrgMap = new HashMap<>();

    public Map<String,SummaryFTG> summaryFTGMap=new HashMap<>();
    public Map<String,SummaryLRG> summaryLRGMap=new HashMap<>();

    public Set<String> ftgmaptoset = new LinkedHashSet<>();
    public Set<String> lrgmaptoset= new LinkedHashSet<>();

    public LocalDate todayDate;

    public LocalDate getTodayDate() {
        return todayDate;
    }

    public  LocalDate getYesturDay(){
        return todayDate.minusDays(1);
    }

    public void setTodayDate(LocalDate todayDate) {
        this.todayDate = todayDate;
    }


    public void addFtgAccountNumberToSet(String accountNumber) {
        System.out.println("AccountNumber:"+accountNumber);
        ftgmaptoset.add(accountNumber);
    }

    public void addLrgAccountNumberToSet(String accountNumber) {
        System.out.println("AccountNumber:"+accountNumber);
        lrgmaptoset.add(accountNumber);
    }

    public Set<String> getLrgmaptoset() {
        return lrgmaptoset;
    }

    public Set<String> getFtgmaptoset() {
        return ftgmaptoset;
    }


    public SummaryFTG getFTG(String accountNumber) {
        if (summaryFTGMap.get(accountNumber) != null) {
            return summaryFTGMap.get(accountNumber);
        }
        else
            return new SummaryFTG();
    }

    public SummaryLRG getLRG(String accountNumber) {
        if (summaryLRGMap.get(accountNumber) != null) {
            return summaryLRGMap.get(accountNumber);
        }
        else
            return new SummaryLRG();
    }

    public Map<Integer, FTG> getFtgMap() {
        return ftgMap;
    }

    public Map<Integer, LRG> getLrgMap() {
        return lrgMap;
    }

    public void populateData(int SLNo, String accountNumber, String accountCurrency, String accountClass, String accountTitle, String trnRefNo, String productCode, String branchCode, String remitterAccountNo, String beneficiaryAccountNo, String instrumentNo, String ccy, Double amount, String valueDate, String activationDate, String status, String dated)
    {
        FTG FTG = ftgMap.get(SLNo);

        if (FTG == null) {
            FTG = new FTG();
        }
        FTG.setAccountNumber(accountNumber);
        FTG.setAccountCurrency(accountCurrency);
        FTG.setAccountClass(accountClass);
        FTG.setAccountTitle(accountTitle);
        FTG.setTrnRefNo(trnRefNo);
        FTG.setProductCode(productCode);
        FTG.setBranchCode(branchCode);
        FTG.setRemitterAccountNo(remitterAccountNo);
        FTG.setBeneficiaryAccountNo(beneficiaryAccountNo);
        FTG.setInstrumentNo(instrumentNo);
        FTG.setCcy(ccy);
        FTG.setAmount(amount);
        FTG.setValueDate(valueDate);
        FTG.setActivationDate(activationDate);
        FTG.setStatus(status);
        FTG.setDated(dated);

        ftgMap.put(SLNo, FTG);

        if(status.equalsIgnoreCase("A")  )
        {
            SummaryFTG summaryFTG = summaryFTGMap.get(accountNumber);

            if (summaryFTG == null) {
                summaryFTG = new SummaryFTG();

                addFtgAccountNumberToSet(accountNumber);

                if(Double.compare(amount, 0.0D) == 0)
                {
                    summaryFTG.setPdcNo(0);
                    summaryFTG.setAmount(0.0);
                }
                else{
                    summaryFTG.setPdcNo(1);
                    summaryFTG.setAmount(amount);
                }

                summaryFTG.setAccountNumber(accountNumber);
                summaryFTG.setAccountCurrency(accountCurrency);
                summaryFTG.setAccountClass(accountClass);
                summaryFTG.setAccountTitle(accountTitle);
            }
            else {

                if(Double.compare(amount, 0.0D) == 0)
                {
                    summaryFTG.setPdcNo(summaryFTG.getPdcNo()+0);
                    summaryFTG.setAmount(summaryFTG.getAmount()+0.0);
                }
                else{
                    summaryFTG.setPdcNo(summaryFTG.getPdcNo()+1);
                    summaryFTG.setAmount(summaryFTG.getAmount()+amount);
                }

            }

            summaryFTGMap.put(accountNumber,summaryFTG);
        }



    }

    public void populateLRGData(int SLNo, String accountNumber, String accountCurrency, String accountClass, String accountTitle, String trnRefNo, String productCode, String branchCode, String remitterAccountNo, String beneficiaryAccountNo, String instrumentNo, String ccy, Double amount, String valueDate, String activationDate, String status, String dated) {

        LRG lrg = lrgMap.get(SLNo);

        if (lrg == null) {
            lrg = new LRG();

        }
        lrg.setAccountNumber(accountNumber);
        lrg.setAccountCurrency(accountCurrency);
        lrg.setAccountClass(accountClass);
        lrg.setAccountTitle(accountTitle);
        lrg.setTrnRefNo(trnRefNo);
        lrg.setProductCode(productCode);
        lrg.setBranchCode(branchCode);
        lrg.setRemitterAccountNo(remitterAccountNo);
        lrg.setBeneficiaryAccountNo(beneficiaryAccountNo);
        lrg.setInstrumentNo(instrumentNo);
        lrg.setCcy(ccy);
        lrg.setAmount(amount);
        lrg.setValueDate(valueDate);
        lrg.setActivationDate(activationDate);
        lrg.setStatus(status);
        lrg.setDated(dated);

        lrgMap.put(SLNo, lrg);

        if(status.equalsIgnoreCase("A"))
        {
            SummaryLRG summaryLRG = summaryLRGMap.get(accountNumber);

            if (summaryLRG == null) {
                summaryLRG = new SummaryLRG();

                addLrgAccountNumberToSet(accountNumber);

                if(Double.compare(amount, 0.0D) == 0)
                {
                    summaryLRG.setPdcNo(0);
                    summaryLRG.setAmount(0.0);
                }
                else {
                    summaryLRG.setPdcNo(1);
                    summaryLRG.setAmount(amount);
                }



                summaryLRG.setAccountNumber(accountNumber);
                summaryLRG.setAccountCurrency(accountCurrency);
                summaryLRG.setAccountClass(accountClass);
                summaryLRG.setAccountTitle(accountTitle);
            }
            else {

                if(Double.compare(amount, 0.0D) == 0)
                {
                    summaryLRG.setPdcNo(summaryLRG.getPdcNo()+0);
                    summaryLRG.setAmount(summaryLRG.getAmount()+0.0);                }
                else {
                    summaryLRG.setPdcNo(summaryLRG.getPdcNo()+1);
                    summaryLRG.setAmount(summaryLRG.getAmount()+amount);
                }

            }

            summaryLRGMap.put(accountNumber,summaryLRG);
        }

    }




    public String doubleToString(double amount)
    {
        if(Double.compare(amount,0.0D)==0)
        {
            return "0.00";
        }
        else {
            DecimalFormat df = new DecimalFormat("#,###.00"); // or pattern "###,###.##$"
            return df.format(amount);
        }
    }

    public String intToString(int count)
    {
        if(count==0)
        {
            return "0";
        }
        else {
            return String.valueOf(count);

        }
    }



    public String isNullOrEmpty(String  value)
    {
        if(value == null || value.isEmpty() || value.equals("\"\""))
        {
            return "-";
        }
        else
            return  value;
    }




    public void getMapData(){

        for (Map.Entry<String, SummaryFTG> mainMap : summaryFTGMap.entrySet()) {

            LOG.info("Printing Map Data");

            LOG.info("AccountNo-> " + mainMap.getKey() + " Account Currency -> " + mainMap.getValue().getAccountCurrency() + " Account Class-> " + mainMap.getValue().getAccountClass() + " PDC NO-> " + mainMap.getValue().getPdcNo() + " Total Amount -> " + mainMap.getValue().getAmount());


        }

    }

    int sr = 1;
    int srLRG=1;

    public int serialNum() {
        return sr++;
    }

    public int serialNumLRG() {
        return srLRG++;
    }


}
