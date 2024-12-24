//Create organization in CRM and verify it is created or not

package com.crm.orgTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.pom.objectRepositoryUtility.CreateOrganizationPage;
import com.comcast.crm.pom.objectRepositoryUtility.HomePage;
import com.comcast.crm.pom.objectRepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.pom.objectRepositoryUtility.OrganizationsPage;
import com.crm.generic.baseutility.BaseClass;

public class OrgTest extends BaseClass {

	@Test(groups = "smoke")
	public void createOrgTest() throws Exception {

		/* 1. Read test script data from Excel*/
		String orgName = eLib.getDataFromExcel("Org", 1, 2) + jLib.getRandomNumber();

		/* 2. navigate to organization page */
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();

		/* 3. click on create organization button */
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgImgLink().click();

		/* 4. Enter the details and create organization */
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrg(orgName);

		/* 5. Verify organization name is created in organization info page using header */
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String header = oip.getHeaderOrgName().getText();
		boolean status = header.contains(orgName);
		Assert.assertEquals(status, true);
	}

	@Test(groups = "regression")
	public void createOrgWithIndustryTest() throws Exception {

		// 1. Read test script data from Excel
		String orgName = eLib.getDataFromExcel("Org", 1, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("Org", 4, 5);

		// 2. navigate to organization page
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();

		// 3. click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgImgLink().click();

		// 4. Enter the details and create organization
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrg(orgName, industry);

		// 5. Verify organization name is created in organization info page using header
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String header = oip.getHeaderOrgName().getText();
		boolean status = header.contains(orgName);
		Assert.assertEquals(status, true);

		// 6. Verify organization is created with industry
		String actIndustry = oip.getIndustry().getText();
		SoftAssert assObj = new SoftAssert();
		assObj.assertEquals(actIndustry, industry);
		assObj.assertAll();
	}

	@Test(groups = "regression")
	public void createOrgWithPhoneNumberTest() throws Exception {

		// 1. Read test script data from Excel
		String orgName = eLib.getDataFromExcel("Org", 1, 2) + jLib.getRandomNumber();
		String phonenum = eLib.getDataFromExcel("Org", 7, 7);

		// 2. navigate to organization page
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();

		// 3. click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgImgLink().click();

		// 4. Enter the details and create organization
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrgWithPhoneNum(orgName, phonenum);

		// 5. Verify organization name is created in organization info page using header
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String header = oip.getHeaderOrgName().getText();
		boolean status = header.contains(orgName);
		Assert.assertEquals(status, true);
	}
}
