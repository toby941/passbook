package de.brendamour.jpasskit.create;

import java.awt.Color;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import de.brendamour.jpasskit.PKBarcode;
import de.brendamour.jpasskit.PKField;
import de.brendamour.jpasskit.PKLocation;
import de.brendamour.jpasskit.PKPass;
import de.brendamour.jpasskit.enums.PKBarcodeFormat;
import de.brendamour.jpasskit.passes.PKStoreCard;
import de.brendamour.jpasskit.signing.PKSigningInformation;
import de.brendamour.jpasskit.signing.PKSigningUtil;

public class PassBookCreateTest {

    @Test
    public void create() throws Exception {
        PKPass pass = new PKPass();
        PKBarcode barcode = new PKBarcode();
        PKStoreCard storeCard = new PKStoreCard();
        List<PKField> primaryFields = new ArrayList<PKField>();

        PKField balanceField = new PKField();
        balanceField.setKey("公司南阳台");
        balanceField.setLabel("公司南阳台");
        balanceField.setValue(20.0);
        balanceField.setCurrencyCode("EUR");

        primaryFields.add(balanceField);

        barcode.setFormat(PKBarcodeFormat.PKBarcodeFormatQR);
        barcode.setMessage("ABCDEFG");
        barcode.setMessageEncoding(Charset.forName("utf-8"));

        storeCard.setPrimaryFields(primaryFields);

        pass.setLocations(getLocations());

        pass.setFormatVersion(1);
        pass.setPassTypeIdentifier("pass.com.airad.toby");
        pass.setSerialNumber("000000001");
        pass.setTeamIdentifier("QBHE7996Q5");
        pass.setBarcode(barcode);
        pass.setOrganizationName("OrgName");
        pass.setLogoText("公司南阳台");
        pass.setStoreCard(storeCard);
        pass.setDescription("My PassBook");
        pass.setBackgroundColorAsObject(Color.BLACK);
        pass.setForegroundColor("rgb(255,255,255 )");
        String passKey = "D:\\tmp\\passbook\\key\\pass.p12";
        String password = "";
        String appleFile = "D:\\tmp\\passbook\\key\\AppleWWDRCA.pem";
        String pathToTemplateDirectory = "D:/tmp/passbook/t";
        PKSigningInformation pkSigningInformation = PKSigningUtil.loadSigningInformationFromPKCS12FileAndIntermediateCertificateFile(passKey,
                password, appleFile);
        byte[] passZipAsByteArray = PKSigningUtil.createSignedAndZippedPkPassArchive(pass, pathToTemplateDirectory, pkSigningInformation);
        FileUtils.forceDelete(new File("D:\\workspace\\emms_maven\\src\\main\\webapp\\WEB-INF\\passbook\\new.pkpass"));
        FileUtils.writeByteArrayToFile(new File("D:\\workspace\\emms_maven\\src\\main\\webapp\\WEB-INF\\passbook\\new.pkpass"),
                passZipAsByteArray);
    }

    /**
     * 智慧谷中心 32.076716,118.748142 <br/>
     * 金城路 32.077275,118.747289 <br/>
     * 智慧谷大门 32.076375,118.747814 <br/>
     * 苏友宾馆 32.075497,118.748978 <br/>
     * ios定位点 32.076784,118.748517 <br/>
     * 金城路下坡 32.075816,118.749837 <br/>
     * ios定位点2 32.076861,118.748463 <br/>
     * ios定位点3 32.076693,118.748485 <br/>
     * ios定位点4 32.076902,118.74819 <br/>
     * ios定位点5 32.076666,118.748313<br/>
     * 苏友宾馆南面空地 32.075425,118.749601 <br/>
     * 苏友宾馆南面空地偏东 32.074975,118.750051 <br/>
     * 丁山公寓楼南 32.076452,118.74915 <br/>
     * 公司南阳台 32.076675,118.748399 <br/>
     * 公司停车场 32.077057,118.747804 <br/>
     * 公司大楼前 32.076647,118.747943 <br/>
     * 公司大楼前2 32.076579,118.748222 <br/>
     * 山坡 32.076538,118.748356 <br/>
     * 山坡2 32.07632,118.748474 <br/>
     * 山坡3 32.076202,118.748506 <br/>
     * 
     * 
     * 
     * @return
     */
    private List<PKLocation> getLocations() {
        List<PKLocation> locationList = new ArrayList<PKLocation>();
        Double[] ds = new Double[] {};
        PKLocation l1 = new PKLocation();
        l1.setLatitude(32.076675); // 维度
        l1.setLongitude(118.748399);// 经度
        l1.setRelevantText("公司南阳台");
        locationList.add(l1);
        return locationList;
    }

}
