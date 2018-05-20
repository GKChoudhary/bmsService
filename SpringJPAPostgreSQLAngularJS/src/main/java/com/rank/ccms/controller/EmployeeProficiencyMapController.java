/*package com.rank.ccms.controller;

import com.rank.futuregenerali.dto.EmployeeMstDto;
import com.rank.futuregenerali.dto.EmployeeProficiencyMapDto;
import com.rank.futuregenerali.dto.LanguageTypeDto;
import com.rank.futuregenerali.entities.CategoryMst;
import com.rank.futuregenerali.entities.EmployeeMst;
import com.rank.futuregenerali.entities.EmployeeProficiencyMap;
import com.rank.futuregenerali.entities.EmployeeTypeMst;
import com.rank.futuregenerali.entities.LanguageMst;
import com.rank.futuregenerali.entities.ServiceMst;
import com.rank.futuregenerali.service.CategoryMstService;
import com.rank.futuregenerali.service.EmployeeMstService;
import com.rank.futuregenerali.service.EmployeeProficiencyMapService;
import com.rank.futuregenerali.service.EmployeeTypeMstService;
import com.rank.futuregenerali.service.LanguageMstService;
import com.rank.futuregenerali.service.ServiceMstService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class EmployeeProficiencyMapController implements Serializable {

    private static final long serialVersionUID = -1767744695003678545L;
    private static final Logger logger = Logger.getLogger(EmployeeProficiencyMapController.class);

    private EmployeeMst employeeMst;
    private List<EmployeeMstDto> listEmployee;
    private List<EmployeeMstDto> filterdListEmployee;
    private EmployeeMstDto selectedEmp = null;
    private List<EmployeeMstDto> selectedEmpList = null;
    private EmployeeTypeMst employeeTypeMst;
    private LanguageTypeDto languageTypeDto;
    private List<LanguageTypeDto> listLanguageTypeDto;
    private List<LanguageTypeDto> listCategoryTypeDto;
    private List<ServiceMst> listService = new ArrayList<>();
    private List<LanguageTypeDto> listServiceTypeDto;
    private EmployeeProficiencyMap employeeProficiencyMap;
    private List<EmployeeProficiencyMap> employeeProficiencyList;
    private List<EmployeeTypeMst> listEmployeeTypeMst;
    private List<EmployeeProficiencyMapDto> selectEmployeeProficiency = new ArrayList<>();
    private EmployeeProficiencyMapDto employeeProficiencyMapDto;
    private CategoryMst categoryMst;
    private LanguageMst languageMst;
    private ServiceMst serviceMst;
    private List<EmployeeProficiencyMapDto> employeeProficienctMapDtolist = new ArrayList<>();
    private List<EmployeeProficiencyMapDto> filteredemployeeProficienctMapDtolist;
    private List<EmployeeMstDto> filteredEmployeeList;
    private Long languageId;
    private Long serviceId;
    private Long categoryId;
    private Long productId;
    private Boolean languageFlag;
    private Boolean categoryFlag;
    private Boolean productFlag = false;
    private Boolean serviceFlag;
    private Boolean noneFlag;
    private Boolean priFlag;
    private String employeeName;
    private List<String> exceptn;
    private List<String> successList;
    private Integer success;
    private Integer error;
    private Integer count1;

    @Autowired
    private EmployeeMstService employeeMstService;
    @Autowired
    private EmployeeTypeMstService employeeTypeMstService;
    @Autowired
    private CategoryMstService categoryMstService;
    @Autowired
    private LanguageMstService languagemstService;
    @Autowired
    private EmployeeProficiencyMapService employeeProficiencyMapService;
    @Autowired
    private ServiceMstService serviceMstService;

    public EmployeeProficiencyMapController() {
        employeeMst = new EmployeeMst();
        employeeTypeMst = new EmployeeTypeMst();
        employeeProficiencyMap = new EmployeeProficiencyMap();
        selectedEmp = new EmployeeMstDto();
        this.listEmployee = new ArrayList<>();
        languageTypeDto = new LanguageTypeDto();
        this.listLanguageTypeDto = new ArrayList<>();
        this.employeeProficiencyList = new ArrayList<>();
        selectedEmpList = new ArrayList<>();
        exceptn = new ArrayList<>();
        successList = new ArrayList<>();
        success = 0;
        error = 0;
        count1 = 0;
    }

    public void loadEmployeeList() {
        listEmployee.clear();
        exceptn = new ArrayList<>();
        successList = new ArrayList<>();
        success = 0;
        error = 0;
        count1 = 0;
        List<EmployeeMst> employeeList = employeeMstService.findAllNonDeletedEmployeeMsts();
        employeeMst = new EmployeeMst();
        listEmployeeTypeMst = new ArrayList<>();
        listEmployeeTypeMst = employeeTypeMstService.findActivenNonDeletedByProperty("typeName", "Underwriter Agent");
        selectedEmpList = new ArrayList<>();
        List<EmployeeMstDto> localEmployeeMstList = new ArrayList<>();
        for (EmployeeMst employeeList1 : employeeList) {
            if (Objects.equals(employeeList1.getEmpTypId().getId(), listEmployeeTypeMst.get(0).getId())) {
                EmployeeMstDto employeeMstDto = new EmployeeMstDto();
                employeeMstDto.setAddrsLine1(employeeList1.getAddrsLine1());
                employeeMstDto.setAddrsLine2(employeeList1.getAddrsLine2());
                employeeMstDto.setCellPhone(employeeList1.getCellPhoneStr());
                employeeMstDto.setCity(employeeList1.getCity());
                employeeMstDto.setCountry(employeeList1.getCountry());
                employeeMstDto.setEmail(employeeList1.getEmail());
                employeeMstDto.setFirstName(employeeList1.getFirstName());
                employeeMstDto.setHomePhone(employeeList1.getHomePhoneStr());
                employeeMstDto.setId(employeeList1.getId().toString());
                employeeMstDto.setLastName(employeeList1.getLastName());
                employeeMstDto.setLoginId(employeeList1.getLoginId());
                employeeMstDto.setLoginPasswd(employeeList1.getLoginPasswd());
                employeeMstDto.setMidName(employeeList1.getMidName());
                employeeMstDto.setOfficePhone(employeeList1.getOfficePhoneStr());
                employeeMstDto.setPin(employeeList1.getPin().toString());
                employeeMstDto.setStateNm(employeeList1.getStateNm());
                localEmployeeMstList.add(employeeMstDto);
            }
        }
        this.setListEmployee(localEmployeeMstList);
        this.setFilterdListEmployee(getListEmployee());
    }

    public void loadSkillAccordingToType() {

        this.noneFlag = true;
        this.categoryFlag = false;
        this.serviceFlag = false;
        this.languageFlag = false;
        this.priFlag = false;

        if (languageTypeDto.getType().equalsIgnoreCase("Category")) {

            this.categoryFlag = true;
            this.serviceFlag = false;
            this.languageFlag = false;
            this.noneFlag = false;
            this.priFlag = true;

        }
        if (languageTypeDto.getType().equalsIgnoreCase("service")) {
            this.categoryFlag = false;
            this.serviceFlag = true;
            this.languageFlag = false;
            this.noneFlag = false;
            this.priFlag = true;

        }

        if (languageTypeDto.getType().equalsIgnoreCase("language")) {
            this.categoryFlag = false;
            this.serviceFlag = false;
            this.languageFlag = true;
            this.noneFlag = false;
            this.priFlag = true;
        }

    }

//    public String saveEmployeeProficiency(HttpServletRequest request) {
//        int check = 0;
//        LanguageTypeDto languageDto = getLanguageTypeDto();
//        employeeProficiencyMap = new EmployeeProficiencyMap();
//        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
//        EmployeeMstDto employeeMstDto = new EmployeeMstDto();
//        employeeMstDto.setAddrsLine1(empmst.getAddrsLine1());
//        if (empmst.getAddrsLine2() != null) {
//            employeeMstDto.setAddrsLine2(empmst.getAddrsLine2());
//        } else {
//            employeeMstDto.setAddrsLine2("");
//        }
//        employeeMstDto.setCellPhone(empmst.getCellPhoneStr());
//
//        if (empmst.getCity() != null) {
//            employeeMstDto.setCity(empmst.getCity());
//        } else {
//            employeeMstDto.setCity("");
//        }
//
//        employeeMstDto.setCountry(empmst.getCountry());
//        employeeMstDto.setEmail(empmst.getEmail());
//        employeeMstDto.setFirstName(empmst.getFirstName());
//        employeeMstDto.setHomePhone(empmst.getHomePhoneStr());
//        employeeMstDto.setId(empmst.getId().toString());
//        employeeMstDto.setLastName(empmst.getLastName());
//        employeeMstDto.setLoginPasswd(empmst.getLoginPasswd());
//        employeeMstDto.setMidName(empmst.getMidName());
//        employeeMstDto.setOfficePhone(empmst.getOfficePhoneStr());
//        employeeMstDto.setPin(empmst.getPin().toString());
//        employeeMstDto.setStateNm(empmst.getStateNm());
//
//        this.setSelectedEmp(employeeMstDto);
//        employeeMst = employeeMstService.findEmployeeMstById(languageDto.getEmpId());
//        employeeTypeMst = employeeTypeMstService.findEmployeeTypeMstById(employeeMst.getEmpTypId().getId());
//        List<EmployeeProficiencyMap> employeeProfMapList;
//        employeeProfMapList = employeeProficiencyMapService.findEmployeeByEmpId(employeeMst);
//        if (languageDto.getType().equalsIgnoreCase("Language")) {
//            languageDto.setId(getLanguageId());
//        } else if (languageDto.getType().equalsIgnoreCase("Service")) {
//            languageDto.setId(getServiceId());
//        } else if (languageDto.getType().equalsIgnoreCase("Category")) {
//            languageDto.setId(getCategoryId());
//        }
//        for (EmployeeProficiencyMap employeeProfMapList1 : employeeProfMapList) {
//            if (employeeProfMapList1.getType().equals(languageDto.getType())) {
//                if (employeeProfMapList1.getSkillId().equals(languageDto.getId())) {
//                    FacesContext.getCurrentInstance().addMessage(null,
//                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! Agent has already been mapped with this skill!", "Agent has already been mapped with this skill!"));
//                    check = 1;
//                    break;
//                }
//            }
//        }
//
//        if (check == 0) {
//            employeeProficiencyMap.setActiveFlg(true);
//            employeeProficiencyMap.setDeleteFlg(false);
//            if (languageDto.getPrimaryOrSecondary().equalsIgnoreCase("primary")) {
//                employeeProficiencyMap.setPrimarySkill((long) 1);
//                employeeProficiencyMap.setSecondarySkill((long) 0);
//
//            } else if (languageDto.getPrimaryOrSecondary().equalsIgnoreCase("secondary")) {
//                employeeProficiencyMap.setPrimarySkill((long) 0);
//                employeeProficiencyMap.setSecondarySkill((long) 1);
//            }
//            employeeProficiencyMap.setType(languageDto.getType());
//            employeeProficiencyMap.setSkillId(languageDto.getId());
//            employeeProficiencyMap.setEmpTypId(employeeMst.getEmpTypId().getId());
//            employeeMst = employeeMstService.findEmployeeMstById(languageDto.getEmpId());
//            employeeProficiencyMap.setEmpId(employeeMst);
//
//            employeeProficiencyMap = employeeProficiencyMapService.saveEmployeeProficiency(employeeProficiencyMap, empmst);
//
//            if (null == employeeProficiencyMap) {
//                FacesContext.getCurrentInstance().addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Mapping Failed!"));
//            } else {
//                languageDto.setType("");
//                languageDto.setPrimaryOrSecondary("");
//                languageDto.setId((long) 0);
//                this.setLanguageId((long) 0);
//                this.setCategoryId((long) 0);
//                this.setServiceId((long) 0);
//
//                FacesContext.getCurrentInstance().addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Mapped Successfully!", ""));
//
//            }
//        }
//        return "/pages/language/addSkillToEmployee.xhtml";
//    }
    public void saveEmployeeProficiencyMultiple(HttpServletRequest request) {

        int check;
        LanguageTypeDto languageDto = getLanguageTypeDto();
        exceptn = new ArrayList<>();
        successList = new ArrayList<>();
        success = 0;
        error = 0;
        count1 = 0;
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        try {

            List<EmployeeProficiencyMap> employeeProfMapList;
            for (EmployeeMstDto selectedEmpList1 : selectedEmpList) {
                EmployeeProficiencyMap employeeProficiencyMapLocal = new EmployeeProficiencyMap();
                check = 0;
                count1++;
                EmployeeMst emp = employeeMstService.findEmployeeMstById(Long.parseLong(selectedEmpList1.getId()));
                String empName;
                if (emp.getMidName() != null && !emp.getMidName().equalsIgnoreCase("")) {
                    empName = emp.getFirstName() + " " + emp.getMidName() + " " + emp.getLastName() + " (" + emp.getLoginId() + ")";
                } else {
                    empName = emp.getFirstName() + " " + emp.getLastName() + " (" + emp.getLoginId() + ")";
                }
                employeeProfMapList = employeeProficiencyMapService.findEmployeeByEmpId(emp);

                if (languageDto.getType().equalsIgnoreCase("Language")) {
                    languageDto.setId(getLanguageId());
                } else if (languageDto.getType().equalsIgnoreCase("Service")) {
                    languageDto.setId(getServiceId());
                } else if (languageDto.getType().equalsIgnoreCase("Category")) {
                    languageDto.setId(getCategoryId());
                }

                for (EmployeeProficiencyMap employeeProfMapList1 : employeeProfMapList) {

                    if (employeeProfMapList1.getType().equals(languageDto.getType())) {
                        if (employeeProfMapList1.getSkillId().equals(languageDto.getId())) {
//                        FacesContext.getCurrentInstance().addMessage(null,
//                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! Agent has already been mapped with this skill!", "Agent has already been mapped with this skill!"));
                            check = 1;
                            error++;
                            String priority;
                            if (employeeProfMapList1.getPrimarySkill() == 1) {
                                priority = "primary";
                            } else {
                                priority = "secondary";
                            }

                            exceptn.add(empName + " has already been mapped with this skill as " + priority + " priority.");
                            break;
                        }
                    }
                }

                if (check == 0) {

                    employeeProficiencyMapLocal.setActiveFlg(true);
                    employeeProficiencyMapLocal.setDeleteFlg(false);
                    if (languageDto.getPrimaryOrSecondary().equalsIgnoreCase("primary")) {
                        employeeProficiencyMapLocal.setPrimarySkill((long) 1);
                        employeeProficiencyMapLocal.setSecondarySkill((long) 0);

                    } else if (languageDto.getPrimaryOrSecondary().equalsIgnoreCase("secondary")) {
                        employeeProficiencyMapLocal.setPrimarySkill((long) 0);
                        employeeProficiencyMapLocal.setSecondarySkill((long) 1);
                    }
                    employeeProficiencyMapLocal.setType(languageDto.getType());
                    employeeProficiencyMapLocal.setSkillId(languageDto.getId());
                    employeeProficiencyMapLocal.setEmpTypId(emp.getEmpTypId().getId());
                    employeeProficiencyMapLocal.setEmpId(emp);
                    employeeProficiencyMapService.saveEmployeeProficiency(employeeProficiencyMapLocal, empmst);
                    successList.add(empName + " Mapped Successfully");
                    success++;
                }
            }
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_INFO, "All Selected Agent(s) Mapped Successfully!", ""));
//            
        } catch (Exception e) {
            logger.error(e.getMessage());
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mapping Failed!", ""));
        } finally {
            if (error > 0) {
                String errorMsg;
                if (success == 0) {
                    errorMsg = "No service assigned to any Agent(s)";
                } else {
                    errorMsg = "Total " + (count1 - success) + " Agent(s) not added";
                }
                getExceptn().add(errorMsg);

            }
            if (success > 0) {
                String successMsg = "Total " + success + " Agent(s) have been added successfully";
                getSuccessList().add(successMsg);
            }
//            count1 = (count - 1);

            selectedEmpList = new ArrayList<>();
            languageDto.setType("");
            languageDto.setPrimaryOrSecondary("");
            languageDto.setId((long) 0);
            this.setLanguageId((long) 0);
            this.setCategoryId((long) 0);
            this.setServiceId((long) 0);

        }
//        return "/pages/language/addSkillToEmployee.xhtml";
    }

    public String back() {
        logger.info("in back");
        loadEmployeeProficiencyMapDto();
        selectedEmpList = new ArrayList<>();
        exceptn = new ArrayList<>();
        successList = new ArrayList<>();
        success = 0;
        error = 0;
        count1 = 0;
        return "/pages/language/employeeSkill.xhtml?faces-redirect=true";
    }

    public void loadEmployeeProficiencyMapDto() {
        selectEmployeeProficiency = new ArrayList<>();
        exceptn = new ArrayList<>();
        successList = new ArrayList<>();
        success = 0;
        error = 0;
        count1 = 0;
        List<EmployeeProficiencyMap> employeeProficiencyListTemp;
        selectEmployeeProficiency = new ArrayList<>();
        employeeProficiencyListTemp = employeeProficiencyMapService.findAllNonDeletedEmployeeProficiencyMaps();
        String type;
        Long languageIdlocal;
        String languageName = null;
        Long primaryLanguage;
        Long secondaryLanguage;
        employeeMst = new EmployeeMst();
        categoryMst = new CategoryMst();
        languageMst = new LanguageMst();
        serviceMst = new ServiceMst();
        employeeProficienctMapDtolist.clear();
        selectedEmpList = new ArrayList<>();
        for (EmployeeProficiencyMap employeeProficiencyListTemp1 : employeeProficiencyListTemp) {
            employeeProficiencyMapDto = new EmployeeProficiencyMapDto();
            employeeMst = employeeMstService.findEmployeeMstById(employeeProficiencyListTemp1.getEmpId().getId());
            type = employeeProficiencyListTemp1.getType();
            languageIdlocal = employeeProficiencyListTemp1.getSkillId();
            if (null != type && type.equalsIgnoreCase("Category")) {
                categoryMst = categoryMstService.findCategoryMstById(languageIdlocal);
                serviceMst = serviceMstService.findAllServiceMstById(languageIdlocal);
                languageName = categoryMst.getCatgDesc();
            }
            if (null != type && type.equalsIgnoreCase("language")) {

                languageMst = languagemstService.findAllLanguageMstById(languageIdlocal);
                languageName = languageMst.getLanguageDesc();
            }
            if (null != type && type.equalsIgnoreCase("Service")) {
                serviceMst = serviceMstService.findAllServiceMstById(languageIdlocal);
                languageName = serviceMst.getServiceDesc();
            }
            primaryLanguage = employeeProficiencyListTemp1.getPrimarySkill();
            if (primaryLanguage == 0) {
                employeeProficiencyMapDto.setPrimary("NO");
            } else {
                employeeProficiencyMapDto.setPrimary("YES");
            }
            secondaryLanguage = employeeProficiencyListTemp1.getSecondarySkill();
            if (secondaryLanguage == 0) {
                employeeProficiencyMapDto.setSecondary("NO");
            } else {
                employeeProficiencyMapDto.setSecondary("YES");
            }
            employeeProficiencyMapDto.setCategoryMst(categoryMst);
            employeeProficiencyMapDto.setEmployeeMst(employeeMst);
            employeeProficiencyMapDto.setServiceMst(serviceMst);
            employeeProficiencyMapDto.setLanguageMst(languageMst);
            employeeProficiencyMapDto.setLanguageType(type);
            employeeProficiencyMapDto.setLanguageId(languageIdlocal);
            employeeProficiencyMapDto.setLanguageName(languageName);
            employeeProficiencyMapDto.setId(employeeProficiencyListTemp1.getId());
            if (null != type && type.equalsIgnoreCase("Service")) {
                employeeProficienctMapDtolist.add(employeeProficiencyMapDto);
            }
        }

        this.setFilteredemployeeProficienctMapDtolist(this.getEmployeeProficienctMapDtolist());
    }

    public String deactivateSkill(HttpServletRequest request) {

        List<EmployeeProficiencyMapDto> selectList = getSelectEmployeeProficiency();
        try {
            EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            for (EmployeeProficiencyMapDto selectList1 : selectList) {
                EmployeeProficiencyMap employeeProficiencyMapping;
                employeeProficiencyMapping = employeeProficiencyMapService.findNonDeletedEmployeeProficiencyById(selectList1.getId());
                employeeProficiencyMapping.setDeleteFlg(true);
                employeeProficiencyMapService.saveEmployeeProficiency(employeeProficiencyMapping, empmst);
            }
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "De-activated Successfully", "De-activated Successfully"));
            loadEmployeeProficiencyMapDto();
            return "/pages/language/deleteEmployeeLanguage.xhtml";
        } catch (Exception e) {

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "failed,Please try again", "Please try again!!"));
            return "/pages/language/deleteEmployeeLanguage.xhtml";

        }
    }

    public String checkEdit() {
        if (!selectedEmpList.isEmpty()) {
            languageTypeDto.setType("");
            languageTypeDto.setId((long) 0);
            languageTypeDto.setPrimaryOrSecondary("");
            this.categoryFlag = false;
            this.serviceFlag = false;
            this.languageFlag = false;
            this.noneFlag = true;
            this.priFlag = false;
            this.setLanguageId((long) 0);
            this.setCategoryId((long) 0);
            this.setServiceId((long) 0);

            listLanguageTypeDto = new ArrayList<>();
            listServiceTypeDto = new ArrayList<>();
            listCategoryTypeDto = new ArrayList<>();

            List<CategoryMst> listCategoryMst;
            listCategoryMst = categoryMstService.finalAllNonDeletedOrderByDesc();
            listCategoryTypeDto.clear();

            for (CategoryMst listCategoryMst1 : listCategoryMst) {
                languageTypeDto = new LanguageTypeDto();
                languageTypeDto.setId(listCategoryMst1.getId());
                languageTypeDto.setName(listCategoryMst1.getCatgName());
                listCategoryTypeDto.add(languageTypeDto);
            }
            setListCategoryTypeDto(listCategoryTypeDto);

            List<ServiceMst> listServiceMst;

            listServiceMst = serviceMstService.findAllNonDeletedServiceMsts();

            listServiceTypeDto.clear();

            for (ServiceMst listServiceMst1 : listServiceMst) {
                languageTypeDto = new LanguageTypeDto();
                languageTypeDto.setId(listServiceMst1.getId());
                languageTypeDto.setName(listServiceMst1.getServiceName());
                listServiceTypeDto.add(languageTypeDto);
            }
            setListServiceTypeDto(listServiceTypeDto);

            List<LanguageMst> listLanguageMst;
            listLanguageMst = languagemstService.findAllNonDeletedLanguageMsts();

            listLanguageTypeDto.clear();

            for (LanguageMst listLanguageMst1 : listLanguageMst) {
                languageTypeDto = new LanguageTypeDto();
                languageTypeDto.setId(listLanguageMst1.getId());
                languageTypeDto.setName(listLanguageMst1.getLanguageName());
                languageTypeDto.setType("Language");
                listLanguageTypeDto.add(languageTypeDto);
            }
//            languageTypeDto = new LanguageTypeDto();
//            languageTypeDto.setEmpId(employeeMst.getId());
            this.employeeName = employeeMst.getFirstName() + " " + employeeMst.getLastName() + " ( " + employeeMst.getLoginId() + " )";
            setListLanguageTypeDto(listLanguageTypeDto);

            return "/pages/language/addSkillToEmployee.xhtml?faces-redirect=true";
        } else {

            RequestContext.getCurrentInstance().execute("editDialog.show();");
            return "";
        }
    }

    public Boolean getPriFlag() {
        return priFlag;
    }

    public void setPriFlag(Boolean priFlag) {
        this.priFlag = priFlag;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Boolean getNoneFlag() {
        return noneFlag;
    }

    public void setNoneFlag(Boolean noneFlag) {
        this.noneFlag = noneFlag;
    }

    public Boolean getLanguageFlag() {
        return languageFlag;
    }

    public void setLanguageFlag(Boolean languageFlag) {
        this.languageFlag = languageFlag;
    }

    public Boolean getCategoryFlag() {
        return categoryFlag;
    }

    public void setCategoryFlag(Boolean categoryFlag) {
        this.categoryFlag = categoryFlag;
    }

    public Boolean getProductFlag() {
        return productFlag;
    }

    public void setProductFlag(Boolean productFlag) {
        this.productFlag = productFlag;
    }

    public Boolean getServiceFlag() {
        return serviceFlag;
    }

    public void setServiceFlag(Boolean serviceFlag) {
        this.serviceFlag = serviceFlag;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<LanguageTypeDto> getListCategoryTypeDto() {
        return listCategoryTypeDto;
    }

    public void setListCategoryTypeDto(List<LanguageTypeDto> listCategoryTypeDto) {
        this.listCategoryTypeDto = listCategoryTypeDto;
    }

    public List<ServiceMst> getListService() {
        return listService;
    }

    public void setListService(List<ServiceMst> listService) {
        this.listService = listService;
    }

    public List<LanguageTypeDto> getListServiceTypeDto() {
        return listServiceTypeDto;
    }

    public void setListServiceTypeDto(List<LanguageTypeDto> listServiceTypeDto) {
        this.listServiceTypeDto = listServiceTypeDto;
    }

    public EmployeeMst getEmployeeMst() {
        return employeeMst;
    }

    public void setEmployeeMst(EmployeeMst employeeMst) {
        this.employeeMst = employeeMst;
    }

    public List<EmployeeMstDto> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(List<EmployeeMstDto> listEmployee) {
        this.listEmployee = listEmployee;
    }

    public EmployeeTypeMst getEmployeeTypeMst() {
        return employeeTypeMst;
    }

    public void setEmployeeTypeMst(EmployeeTypeMst employeeTypeMst) {
        this.employeeTypeMst = employeeTypeMst;
    }

    public EmployeeMstDto getSelectedEmp() {
        return selectedEmp;
    }

    public void setSelectedEmp(EmployeeMstDto selectedEmp) {

        if (selectedEmp != null) {

            setEmployeeMst(employeeMstService.findEmployeeMstById(Long.parseLong(selectedEmp.getId())));
            this.selectedEmp = selectedEmp;
        } else {
            this.selectedEmp = null;
        }

    }

    public LanguageTypeDto getLanguageTypeDto() {
        return languageTypeDto;
    }

    public void setLanguageTypeDto(LanguageTypeDto languageTypeDto) {
        this.languageTypeDto = languageTypeDto;
    }

    public List<LanguageTypeDto> getListLanguageTypeDto() {
        return listLanguageTypeDto;
    }

    public void setListLanguageTypeDto(List<LanguageTypeDto> listLanguageTypeDto) {
        this.listLanguageTypeDto = listLanguageTypeDto;
    }

    public EmployeeProficiencyMap getEmployeeProficiencyMap() {
        return employeeProficiencyMap;
    }

    public void setEmployeeProficiencyMap(EmployeeProficiencyMap employeeProficiencyMap) {
        this.employeeProficiencyMap = employeeProficiencyMap;
    }

    public List<EmployeeProficiencyMap> getEmployeeProficiencyList() {
        return employeeProficiencyList;
    }

    public void setEmployeeProficiencyList(List<EmployeeProficiencyMap> employeeProficiencyList) {
        this.employeeProficiencyList = employeeProficiencyList;
    }

    public List<EmployeeTypeMst> getListEmployeeTypeMst() {
        return listEmployeeTypeMst;
    }

    public void setListEmployeeTypeMst(List<EmployeeTypeMst> listEmployeeTypeMst) {
        this.listEmployeeTypeMst = listEmployeeTypeMst;
    }

    public List<EmployeeProficiencyMapDto> getSelectEmployeeProficiency() {
        return selectEmployeeProficiency;
    }

    public void setSelectEmployeeProficiency(List<EmployeeProficiencyMapDto> selectEmployeeProficiency) {
        this.selectEmployeeProficiency = selectEmployeeProficiency;
    }

    public EmployeeProficiencyMapDto getEmployeeProficiencyMapDto() {
        return employeeProficiencyMapDto;
    }

    public void setEmployeeProficiencyMapDto(EmployeeProficiencyMapDto employeeProficiencyMapDto) {
        this.employeeProficiencyMapDto = employeeProficiencyMapDto;
    }

    public CategoryMst getCategoryMst() {
        return categoryMst;
    }

    public LanguageMst getLanguageMst() {
        return languageMst;
    }

    public void setLanguageMst(LanguageMst languageMst) {
        this.languageMst = languageMst;
    }

    public void setCategoryMst(CategoryMst categoryMst) {
        this.categoryMst = categoryMst;
    }

    public List<EmployeeProficiencyMapDto> getEmployeeProficienctMapDtolist() {
        return employeeProficienctMapDtolist;
    }

    public void setEmployeeProficienctMapDtolist(List<EmployeeProficiencyMapDto> employeeProficienctMapDtolist) {
        this.employeeProficienctMapDtolist = employeeProficienctMapDtolist;
    }

    public List<EmployeeProficiencyMapDto> getFilteredemployeeProficienctMapDtolist() {
        return filteredemployeeProficienctMapDtolist;
    }

    public void setFilteredemployeeProficienctMapDtolist(List<EmployeeProficiencyMapDto> filteredemployeeProficienctMapDtolist) {
        this.filteredemployeeProficienctMapDtolist = filteredemployeeProficienctMapDtolist;
    }

    public List<EmployeeMstDto> getFilteredEmployeeList() {
        return filteredEmployeeList;
    }

    public void setFilteredEmployeeList(List<EmployeeMstDto> filteredEmployeeList) {
        this.filteredEmployeeList = filteredEmployeeList;
    }

    public List<EmployeeMstDto> getFilterdListEmployee() {
        return filterdListEmployee;
    }

    public void setFilterdListEmployee(List<EmployeeMstDto> filterdListEmployee) {
        this.filterdListEmployee = filterdListEmployee;
    }

    public List<EmployeeMstDto> getSelectedEmpList() {
        return selectedEmpList;
    }

    public void setSelectedEmpList(List<EmployeeMstDto> selectedEmpList) {
        this.selectedEmpList = selectedEmpList;
    }

    public List<String> getExceptn() {
        return exceptn;
    }

    public void setExceptn(List<String> exceptn) {
        this.exceptn = exceptn;
    }

    public List<String> getSuccessList() {
        return successList;
    }

    public void setSuccessList(List<String> successList) {
        this.successList = successList;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public Integer getCount1() {
        return count1;
    }

    public void setCount1(Integer count1) {
        this.count1 = count1;
    }

}
*/