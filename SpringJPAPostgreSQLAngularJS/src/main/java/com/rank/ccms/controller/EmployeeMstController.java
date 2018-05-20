/*package com.rank.ccms.controller;

import com.rank.futuregenerali.dto.EmployeeMstDto;
import com.rank.futuregenerali.entities.EmployeeMst;
import com.rank.futuregenerali.entities.EmployeeProficiencyMap;
import com.rank.futuregenerali.entities.EmployeeTypeMst;
import com.rank.futuregenerali.service.CategoryMstService;
import com.rank.futuregenerali.service.EmployeeMstService;
import com.rank.futuregenerali.service.EmployeeProficiencyMapService;
import com.rank.futuregenerali.service.EmployeeTypeMstService;
import com.rank.futuregenerali.service.LanguageMstService;
import com.rank.futuregenerali.service.ServiceMstService;
import com.rank.futuregenerali.util.ThreadSafeSimpleDateFormat;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.validator.EmailValidator;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class EmployeeMstController implements Serializable {

    *//**
     * serialVersionUID
     *//*
    private static final long serialVersionUID = 4995547026571725597L;
    private static final  Logger logger = Logger.getLogger(EmployeeMstController.class);
    
    private EmployeeMst employeeMst;
    private EmployeeTypeMst employeeTypeMst;
    private List<EmployeeMstDto> listEmployee;
    private List<EmployeeMstDto> selectEmployees;   
    private String cell = "";
    private String pin = "";
    private String officePhone = "";
    private String homePhone = "";
    private Long empTypeId;
    private List<EmployeeMstDto> filteredEmployee;
    private String password;
   

    @Autowired
    private EmployeeMstService employeeMstService;
    @Autowired
    private EmployeeTypeMstService employeeTypeMstService;
    @Autowired
    private EmployeeProficiencyMapService employeeProficiencyMapService;
    @Autowired
    private LanguageMstService languageMstService;
    @Autowired
    private CategoryMstService categoryMstService;
    @Autowired
    private ServiceMstService serviceMstService;

    public EmployeeMstController() {
        employeeMst = new EmployeeMst();
        employeeTypeMst = new EmployeeTypeMst();
        this.listEmployee = new ArrayList<>();
        this.selectEmployees = new ArrayList<>();
    }

    public EmployeeMst getEmployeeMst() {
        return employeeMst;
    }

    public void setEmployeeMst(EmployeeMst employeeMst) {
        this.employeeMst = employeeMst;
    }

    public EmployeeTypeMst getEmployeeTypeMst() {
        return employeeTypeMst;
    }

    public void setEmployeeTypeMst(EmployeeTypeMst employeeTypeMst) {
        this.employeeTypeMst = employeeTypeMst;
    }

    public List<EmployeeMstDto> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(List<EmployeeMstDto> listEmployee) {
        this.listEmployee = listEmployee;
    }

    public List<EmployeeMst> getAllEmployee() {
        return employeeMstService.findAllNonDeletedEmployeeMsts();

    }

    public void newEmployeeMstComponent() {
        employeeMst = new EmployeeMst();
        employeeTypeMst = new EmployeeTypeMst();
        cell = "";
        pin = "";
        officePhone = "";
        homePhone = "";
        this.password = "";
    }

    public String save(HttpServletRequest request) {
        String requestAddIp = request.getRemoteAddr();
        EmployeeMst l_employeeMst = getEmployeeMst();
        l_employeeMst.setActiveFlg(true);
        l_employeeMst.setDeleteFlg(false);

        if ("".equals(this.getPin().trim())) {
            l_employeeMst.setPin(0);
        } else {
            l_employeeMst.setPin(Integer.parseInt(this.getPin()));
        }

        if ("".equals(this.getOfficePhone().trim())) {
            l_employeeMst.setOfficePhone(Long.parseLong("0"));
        } else {
            l_employeeMst.setOfficePhone(Long.parseLong(this.getOfficePhone()));
        }

        if ("".equals(this.getHomePhone().trim())) {
            l_employeeMst.setHomePhone(Long.parseLong("0"));
        } else {
            l_employeeMst.setHomePhone(Long.parseLong(this.getHomePhone()));
        }

        if ("".equals(l_employeeMst.getOfficePhoneStr().trim())) {
            l_employeeMst.setOfficePhone(Long.parseLong("0"));
        } else {
            l_employeeMst.setOfficePhone(Long.parseLong(l_employeeMst.getOfficePhoneStr()));
        }

        if ("".equals(l_employeeMst.getHomePhoneStr().trim())) {
            l_employeeMst.setHomePhone(Long.parseLong("0"));
        } else {
            l_employeeMst.setHomePhone(Long.parseLong(l_employeeMst.getHomePhoneStr()));
        }

        l_employeeMst.setCellPhone(Long.parseLong(this.getCell()));
        l_employeeMst.setLoginPasswd(this.password);
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        employeeTypeMst = employeeTypeMstService.findEmployeeTypeMstById(getEmployeeTypeMst().getId());  
        l_employeeMst.setEmpTypId(employeeTypeMst);

        try {

            l_employeeMst = employeeMstService.saveEmployeeMst(employeeMst, empmst, requestAddIp);

            if (null == l_employeeMst) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! could not create employee", ""));
                return "/pages/employee/createEmployee.xhtml";
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved Successfully!", ""));
                employeeMst = new EmployeeMst();
                this.pin = "";
                this.cell = "";
                this.homePhone = "";
                this.officePhone = "";
                this.password = "123";
                
                employeeTypeMst = new EmployeeTypeMst();
                
                addDefaultSkills(l_employeeMst, "Language");
                addDefaultSkills(l_employeeMst, "Category");
                addDefaultSkills(l_employeeMst, "Service");
                loadEmployeeList();
                
                return "/pages/employee/createEmployee.xhtml";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! could not create employee", ""));
            return "/pages/employee/createEmployee.xhtml";

        }
    }

    public void editDefaultSkills(EmployeeMst empmst) {

        try {

            List<EmployeeProficiencyMap> employeeProficiencyMapList = employeeProficiencyMapService.findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(empmst.getId());
            for (EmployeeProficiencyMap empPrfMap : employeeProficiencyMapList) {
                empPrfMap.setEmpTypId(empmst.getEmpTypId().getId());
                employeeProficiencyMapService.saveEmployeeProficiency(empPrfMap, empmst);
            }
        } catch (Exception e) {
            logger.info("editDefaultSkills error :" +e);
        }

    }

    public void addDefaultSkills(EmployeeMst empmst, String type) {

        try {
            EmployeeProficiencyMap employeeProficiencyMap = new EmployeeProficiencyMap();
            EmployeeMstDto employeeMstDto = new EmployeeMstDto();
            employeeMstDto.setAddrsLine1(empmst.getAddrsLine1());
            if (empmst.getAddrsLine2() != null) {
                employeeMstDto.setAddrsLine2(empmst.getAddrsLine2());
            } else {
                employeeMstDto.setAddrsLine2("");
            }
            employeeMstDto.setCellPhone(empmst.getCellPhoneStr());

            if (empmst.getCity() != null) {
                employeeMstDto.setCity(empmst.getCity());
            } else {
                employeeMstDto.setCity("");
            }
            Long skillId = (long) 0;

            employeeMstDto.setCountry(empmst.getCountry());
            employeeMstDto.setEmail(empmst.getEmail());
            employeeMstDto.setFirstName(empmst.getFirstName());
            employeeMstDto.setHomePhone(empmst.getHomePhoneStr());
            employeeMstDto.setId(empmst.getId().toString());

            employeeMstDto.setLastName(empmst.getLastName());
            employeeMstDto.setLoginPasswd(empmst.getLoginPasswd());
            employeeMstDto.setMidName(empmst.getMidName());
            employeeMstDto.setOfficePhone(empmst.getOfficePhoneStr());
            employeeMstDto.setPin(empmst.getPin().toString());
            employeeMstDto.setStateNm(empmst.getStateNm());

            if (type.equalsIgnoreCase("Language")) {
                skillId = languageMstService.findLanguageMstByLanguageName("English").getId();

            } else if (type.equalsIgnoreCase("Category")) {
                skillId = categoryMstService.findCategoryByCategoryName("Services").getId();

            }else if (type.equalsIgnoreCase("Service")) {
                skillId = serviceMstService.findServiceByServiceName("Account Services").getId();
            }
            

            employeeProficiencyMap.setActiveFlg(true);
            employeeProficiencyMap.setDeleteFlg(false);
            employeeProficiencyMap.setPrimarySkill((long) 1);
            employeeProficiencyMap.setSecondarySkill((long) 0);

            employeeProficiencyMap.setType(type);
            employeeProficiencyMap.setSkillId(skillId);
            employeeProficiencyMap.setEmpTypId(empmst.getEmpTypId().getId());
            employeeProficiencyMap.setEmpId(empmst);

            employeeProficiencyMapService.saveEmployeeProficiency(employeeProficiencyMap, empmst);
        } catch (Exception e) {
            logger.error("addDefaultSkills error :" +e);
        }

    }

    public String update(HttpServletRequest request) {
        String requestAddIp = request.getRemoteAddr();
        EmployeeMst emp = getEmployeeMst();

        if ("".equals(this.getPin().trim())) {
            emp.setPin(0);
        } else {
            emp.setPin(Integer.parseInt(this.getPin()));
        }

        if ("".equals(this.getOfficePhone().trim())) {
            emp.setOfficePhone(Long.parseLong("0"));
        } else {
            emp.setOfficePhone(Long.parseLong(this.getOfficePhone()));
        }

        if ("".equals(this.getHomePhone().trim())) {
            emp.setHomePhone(Long.parseLong("0"));
        } else {
            emp.setHomePhone(Long.parseLong(this.getHomePhone()));
        }

        emp.setCellPhone(Long.parseLong(this.getCell()));

        employeeTypeMst = employeeTypeMstService.findEmployeeTypeMstById(this.getEmpTypeId());
        emp.setEmpTypId(employeeTypeMst);

        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        emp = employeeMstService.saveEmployeeMst(emp, empmst, requestAddIp);

        if (null == emp) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to update Employee ", "Please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/pages/employee/editEmployee.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee id " + emp.getId() + " updated Successfully!    ", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            editDefaultSkills(emp);
            return "/pages/employee/editEmployee.xhtml";
        }
    }

    public void activate(HttpServletRequest request) {
        String requestAddIp = request.getRemoteAddr();
        List<EmployeeMstDto> employeeMstList = getSelectEmployees();
        try {
            EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            for (EmployeeMstDto employeeMstList1 : employeeMstList) {
                EmployeeMst emp;
                emp = employeeMstService.findEmployeeMstById(Long.parseLong(employeeMstList1.getId()));
                emp.setActiveFlg(true);
                employeeMstService.saveEmployeeMst(emp, empmst, requestAddIp);
            }
            loadEmployeeList();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Activated!", ""));

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Activation Failed!", ""));

        }
    }

    public void deActivate(HttpServletRequest request) {
        String requestAddIp = request.getRemoteAddr();
        List<EmployeeMstDto> employeeMstList = getSelectEmployees();
        try {
            EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            for (EmployeeMstDto employeeMstList1 : employeeMstList) {
                EmployeeMst emp;
                emp = employeeMstService.findEmployeeMstById(Long.parseLong(employeeMstList1.getId()));
                emp.setActiveFlg(true);
                employeeMstService.saveEmployeeMst(emp, empmst, requestAddIp);
            }
            loadEmployeeList();
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activation Failed!", ""));
        }
    }

    public String back() {
        loadEmployeeList();
        return "/pages/employee/listEmployee.xhtml?faces-redirect=true";
    }

    public void selectEmployeeById(Long id) {
        setEmployeeMst(employeeMstService.findEmployeeMstById(id));
        setEmployeeTypeMst(employeeTypeMstService.findEmployeeTypeMstById(getEmployeeMst().getEmpTypId().getId()));

    }

    public void onEdit(RowEditEvent eve) {
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
        HttpServletRequest request = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());

        String requestAddIp = request.getRemoteAddr();
        EmployeeMstDto empMstDto = (EmployeeMstDto) eve.getObject();
        EmployeeMst empMst = employeeMstService.findEmployeeMstById(Long.parseLong(empMstDto.getId()));
        EmployeeTypeMst employeeTypeMstLocal = employeeTypeMstService.findEmployeeTypeMstById(Long.parseLong(empMstDto.getEmployeeTypeId()));
        empMst.setEmpTypId(employeeTypeMstLocal);
        empMstDto.setEmployeeType(employeeTypeMstLocal.getTypeName());
        empMst.setFirstName(empMstDto.getFirstName());
        empMst.setMidName(empMstDto.getMidName());
        empMst.setLastName(empMstDto.getLastName());
        empMst.setLoginPasswd(empMstDto.getLoginPasswd());
        empMst.setAddrsLine1(empMstDto.getAddrsLine1());
        empMst.setAddrsLine2(empMstDto.getAddrsLine2());
        empMst.setCity(empMstDto.getCity());
        empMst.setStateNm(empMstDto.getStateNm());
        
        if (!empMstDto.getPin().trim().equals("")) {
            empMst.setPin(Integer.parseInt(empMstDto.getPin()));
        } else {
            empMst.setPin(Integer.parseInt("0"));
        }

        empMst.setCountry(empMstDto.getCountry());
        empMst.setEmail(empMstDto.getEmail());
        if (!empMstDto.getOfficePhone().trim().equals("")) {
            empMst.setOfficePhone(Long.parseLong(empMstDto.getOfficePhone()));
        } else {
            empMst.setOfficePhone(Long.parseLong("0"));
        }
        if (!empMstDto.getHomePhone().trim().equals("")) {
            empMst.setHomePhone(Long.parseLong(empMstDto.getHomePhone()));
        } else {
            empMst.setHomePhone(Long.parseLong("0"));
        }
        if (!empMstDto.getCellPhone().trim().equals("")) {
            empMst.setCellPhone(Long.parseLong(empMstDto.getCellPhone()));
        } else {
            empMst.setCellPhone(Long.parseLong("0"));
        }
        EmployeeMst emp = (EmployeeMst) session.getAttribute("ormUserMaster");
        empMst = employeeMstService.saveEmployeeMst(empMst, emp, requestAddIp);
        if (empMst == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to Update Employee id "
                    + ((EmployeeMstDto) eve.getObject()).getId(), "Please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee id "
                    + ((EmployeeMstDto) eve.getObject()).getId() + " updated Successfully!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            editDefaultSkills(empMst);
        }
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Update Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void delete(HttpServletRequest request) {
        String requestAddIp = request.getRemoteAddr();
        List<EmployeeMstDto> employeeMstList = getSelectEmployees();
        try {
            EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");

            for (EmployeeMstDto employeeMstList1 : employeeMstList) {
                EmployeeMst emp;
                emp = employeeMstService.findEmployeeMstById(Long.parseLong(employeeMstList1.getId()));
                emp.setDeleteFlg(true);
                employeeMstService.saveEmployeeMst(emp, empmst, requestAddIp);

            }

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted Successfully!", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletion Failed!", ""));

        } finally {
            loadEmployeeList();
        }
    }

    public void deactivateEmployee(HttpServletRequest request) {
        String requestAddIp = request.getRemoteAddr();
        try {
            EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            EmployeeMst emp;
            emp = employeeMstService.findEmployeeMstById(getEmployeeMst().getId());
            emp.setDeactivateFlg(true);
            employeeMstService.saveEmployeeMst(emp, empmst, requestAddIp);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activated Successfully!", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activation Failed!", ""));

        } finally {
            loadEmployeeList();
        }

    }

    public void activateEmployee(HttpServletRequest request) {
        String requestAddIp = request.getRemoteAddr();
        try {
            EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            EmployeeMst emp;
            emp = employeeMstService.findEmployeeMstById(getEmployeeMst().getId());
            emp.setDeactivateFlg(false);
            employeeMstService.saveEmployeeMst(emp, empmst, requestAddIp);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Activated Successfully!", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Activation Failed!", ""));

        } finally {
            loadEmployeeList();
        }

    }

    public String checkEdit() {

        if (this.selectEmployees.size() > 0) {
            return "/pages/employee/editEmployee.xhtml?faces-redirect=true";
        } else {
            RequestContext.getCurrentInstance().execute("empNoeditViewDialog.show();");
            return "/pages/employee/listEmployee.xhtml";
        }
    }

    public List<EmployeeMstDto> getSelectEmployees() {
        return selectEmployees;
    }

    public void setSelectEmployees(List<EmployeeMstDto> selectEmployees) {

        if (selectEmployees.size() == 1) {
            this.setEmployeeMst(employeeMstService.findEmployeeMstById(Long.parseLong(selectEmployees.get(0).getId())));
            EmployeeTypeMst employeeTypeMstl = employeeMstService.findAllEmployeeByUserId(getEmployeeMst().getLoginId()).getEmpTypId();
            this.password = getEmployeeMst().getLoginPasswd();
            if (null != getEmployeeMst().getPin() && getEmployeeMst().getPin() != 0) {
                this.pin = getEmployeeMst().getPin().toString();
            } else {
                this.pin = "";
            }
            if (null != getEmployeeMst().getHomePhone() && getEmployeeMst().getHomePhone() != 0) {
                this.homePhone = getEmployeeMst().getHomePhone().toString();
            } else {
                this.homePhone = "";
            }
            if (null != getEmployeeMst().getOfficePhone() && getEmployeeMst().getOfficePhone() != 0) {
                this.officePhone = getEmployeeMst().getOfficePhone().toString();
            } else {
                this.officePhone = "";
            }
            if (null != getEmployeeMst().getCellPhone() && getEmployeeMst().getCellPhone() != 0) {
                this.cell = getEmployeeMst().getCellPhone().toString();
            } else {
                this.cell = "";
            }

            this.setEmpTypeId(employeeTypeMstl.getId());
            this.getEmployeeMst().setEmpTypId(employeeTypeMstl);

        }

        this.selectEmployees = selectEmployees;
    }

    public void checkErrors() {

    }

    public void callRegister() {
        RequestContext.getCurrentInstance().execute("register();");
    }

    public void checkDuplicateEmailId(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newEmailId = value.toString();

        if (!value.toString().trim().equals("")) {

            if (newEmailId.length() == newEmailId.trim().length()) {
                boolean valid = EmailValidator.getInstance().isValid(newEmailId);
                if (valid) {
                    EmployeeMst l_employeeMst = employeeMstService.findAllEmployeeByEmailId(newEmailId);
                    if (l_employeeMst != null) {
                        FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error: Email Id is already been registered ", "Error: Email Id is already been registered");
                        throw new ValidatorException(fma);
                    }
                } else {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Email: Validation Error:Invalid email format ", "Email: Validation Error:Invalid email format");
                    throw new ValidatorException(fma);
                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Email Id: Validation Error: Please remove leading and trailing spaces ", "Email Id: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email Id: Validation Error: Email Id can not be blank", "Email Id: Validation Error:Email Id can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkDuplicateLogId(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newUsername = value.toString();
        if (!value.toString().trim().equals("")) {

            if (newUsername.length() == newUsername.trim().length()) {
                EmployeeMst l_employeeMst = employeeMstService.findAllEmployeeByUserId(newUsername);
                if (l_employeeMst != null) {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error: Login Id is already been used ", "Error: Login Id is already been used");
                    throw new ValidatorException(fma);
                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Login Id: Validation Error: Please remove leading and trailing spaces ", "Login Id: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Login Id: Validation Error: Login Id can not be blank", "Login Id: Validation Error:Login Id can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkDuplicateLogIdEdit(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newUserId = value.toString();
        Long empId = (Long) component.getAttributes().get("empId");

        if (!value.toString().trim().equals("")) {

            if (newUserId.length() == newUserId.trim().length()) {

                EmployeeMst l_employeeMst = employeeMstService.findAllEmployeeByUserId(newUserId);
                if (l_employeeMst != null) {

                    if (!Objects.equals(l_employeeMst.getId(), empId)) {
                        FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error: Login Id is already been used ", "Error: Login Id is already been used");
                        throw new ValidatorException(fma);
                    }
                }

            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Login Id: Validation Error: Please remove leading and trailing spaces ", "Login Id: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Login Id: Validation Error: Login Id can not be blank", "Login Id: Validation Error:Login Id can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkDuplicateEmailIdEdit(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newEmailId = value.toString();
        Long empId = (Long) component.getAttributes().get("empId");

        if (!value.toString().trim().equals("")) {

            if (newEmailId.length() == newEmailId.trim().length()) {
                boolean valid = EmailValidator.getInstance().isValid(newEmailId);
                if (valid) {
                    EmployeeMst l_employeeMst = employeeMstService.findAllEmployeeByEmailId(newEmailId);
                    if (l_employeeMst != null) {

                        if (!Objects.equals(l_employeeMst.getId(), empId)) {
                            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Error: Email Id is already been registered ", "Error: Email Id is already been registered");
                            throw new ValidatorException(fma);
                        }
                    }
                } else {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Email: Validation Error:Invalid email format ", "Email: Validation Error:Invalid email format");
                    throw new ValidatorException(fma);
                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Email Id: Validation Error: Please remove leading and trailing spaces ", "Email Id: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email Id: Validation Error: Email Id can not be blank", "Email Id: Validation Error:Email Id can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkDuplicateEmailIdEditMultiple(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newEmailId = value.toString();
        String empId = (String) component.getAttributes().get("empId");
        Long eid = Long.parseLong(empId);

        if (!value.toString().trim().equals("")) {

            if (newEmailId.length() == newEmailId.trim().length()) {
                boolean valid = EmailValidator.getInstance().isValid(newEmailId);
                if (valid) {
                    EmployeeMst l_employeeMst = employeeMstService.findAllEmployeeByEmailId(newEmailId);
                    if (l_employeeMst != null) {

                        if (!Objects.equals(l_employeeMst.getId(), eid)) {
                            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Error: Email Id is already been registered ", "Error: Email Id is already been registered");
                            throw new ValidatorException(fma);
                        }
                    }
                } else {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Email: Validation Error:Invalid email format ", "Email: Validation Error:Invalid email format");
                    throw new ValidatorException(fma);
                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Email Id: Validation Error: Please remove leading and trailing spaces ", "Email Id: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email Id: Validation Error: Email Id can not be blank", "Email Id: Validation Error:Email Id can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkDuplicateLogIdEditMultiple(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newUsername = value.toString();
        String empId = (String) component.getAttributes().get("empId");
        Long eid = Long.parseLong(empId);

        if (!value.toString().trim().equals("")) {

            if (newUsername.length() == newUsername.trim().length()) {
                EmployeeMst l_employeeMst = employeeMstService.findAllEmployeeByUserId(newUsername);
                if (l_employeeMst != null) {

                    if (!Objects.equals(l_employeeMst.getId(), eid)) {
                        FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error: Login Id is already been used ", "Error: Login Id is already been used");
                        throw new ValidatorException(fma);
                    }
                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Login Id: Validation Error: Please remove leading and trailing spaces ", "Login Id: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Login Id: Validation Error: Login Id can not be blank", "Login Id: Validation Error:Login Id can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkAddress(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String address = value.toString();
        if (!value.toString().trim().equals("")) {

            if (address.length() != address.trim().length()) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Address 1: Validation Error: Please remove leading and trailing spaces ", "Address 1: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Address 1: Validation Error: Address 1 can not be blank", "Address 1: Validation Error:Address 1 can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkAddress1(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String address = value.toString();
        if (!value.toString().trim().equals("")) {

            if (address.length() != address.trim().length()) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Address 2: Validation Error: Please remove leading and trailing spaces ", "Address 1: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        }
    }

    public void checkCity(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String city = value.toString();
        if (!value.toString().trim().equals("")) {

            if (city.length() != city.trim().length()) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "City: Validation Error: Please remove leading and trailing spaces ", "City: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            } else {
                Pattern p = Pattern.compile("^$|^[a-zA-Z ]+$");
                Matcher m = p.matcher(city);
                if (!m.matches()) {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "City: Validation Error: City can not be blank", "City: Validation Error:Only characters and spaces allowed");
                }
            }
        }
    }

    public void checkState(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String state = value.toString();
        if (!value.toString().trim().equals("")) {

            if (state.length() != state.trim().length()) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "State: Validation Error: Please remove leading and trailing spaces ", "State: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            } else {
                Pattern p = Pattern.compile("^$|^[a-zA-Z ]+$");
                Matcher m = p.matcher(state);
                if (!m.matches()) {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "State: Validation Error: City can not be blank", "State: Validation Error:Only characters and spaces allowed");
                }
            }
        }
    }

    public void checkCountry(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String country = value.toString();
        if (!value.toString().trim().equals("")) {

            if (country.length() != country.trim().length()) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Country: Validation Error: Please remove leading and trailing spaces ", "Country: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            } else {
                Pattern p = Pattern.compile("^$|^[a-zA-Z ]+$");
                Matcher m = p.matcher(country);
                if (!m.matches()) {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Country: Validation Error: City can not be blank", "Country: Validation Error:Only characters and spaces allowed");
                }
            }
        }
    }

    public void checkPin(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String pinn = value.toString();
        if (!value.toString().trim().equals("")) {

            if (pinn.length() != 6) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Pin: Validation Error:Only numeric value with 6 digits allowed", "Pin: Validation Error:Only numeric value with 6 digits allowed");
                throw new ValidatorException(fma);
            }
        }
    }

    public void checkOfficePhone(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String offph = value.toString();
        if (!value.toString().trim().equals("")) {

            if (offph.length() < 10) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Office Phone: Validation Error:Only numeric value with minimum 10 digits allowed", "Office Phone: Validation Error:Only numeric value with minimum 10 digits allowed");
                throw new ValidatorException(fma);
            }
        }
    }

    public void checkHomePhone(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String hph = value.toString();
        if (!value.toString().trim().equals("")) {

            if (hph.length() < 10) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Home Phone: Validation Error:Only numeric value with minimum 10 digits allowed", "Home Phone: Validation Error:Only numeric value with minimum 10 digits allowed");
                throw new ValidatorException(fma);
            }
        }
    }

    public void loadEmployeeList() {
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
        EmployeeMst employeeMstL = (EmployeeMst) session.getAttribute("ormAdminMaster");
        SimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MM-yyyy HH:mm");
        selectEmployees.clear();
        List<EmployeeMst> employeeMstList = employeeMstService.findAllNonDeletedEmployeeMsts();
        List<EmployeeMstDto> employeeMstListDto = new ArrayList<>();
        for (EmployeeMst employeeMstList1 : employeeMstList) {

            EmployeeMstDto employeeMstDto = new EmployeeMstDto();
            employeeMstDto.setId(employeeMstList1.getId().toString());
            employeeMstDto.setFirstName(employeeMstList1.getFirstName());
            employeeMstDto.setMidName(employeeMstList1.getMidName());
            employeeMstDto.setLastName(employeeMstList1.getLastName());
            employeeMstDto.setLoginId(employeeMstList1.getLoginId());
            employeeMstDto.setLoginPasswd(employeeMstList1.getLoginPasswd());
            if (employeeMstList1.getEmpDob() != null) {
                employeeMstDto.setEmpDob(sdf.format(employeeMstList1.getEmpDob()));
            } else {
                employeeMstDto.setEmpDob("");
            }
            employeeMstDto.setAddrsLine1(employeeMstList1.getAddrsLine1());
            employeeMstDto.setAddrsLine2(employeeMstList1.getAddrsLine2());
            employeeMstDto.setCity(employeeMstList1.getCity());
            employeeMstDto.setStateNm(employeeMstList1.getStateNm());

            if (null != employeeMstList1.getPin() && employeeMstList1.getPin() != 0) {
                employeeMstDto.setPin(employeeMstList1.getPin().toString());
            } else {
                employeeMstDto.setPin("");
            }
            employeeMstDto.setCountry(employeeMstList1.getCountry());
            employeeMstDto.setEmail(employeeMstList1.getEmail());
            if (null != employeeMstList1.getHomePhone() && employeeMstList1.getHomePhone() != 0) {
                employeeMstDto.setHomePhone(employeeMstList1.getHomePhone().toString());
            } else {
                employeeMstDto.setHomePhone("");
            }
            if (null != employeeMstList1.getOfficePhone() && employeeMstList1.getOfficePhone() != 0) {
                employeeMstDto.setOfficePhone(employeeMstList1.getOfficePhone().toString());
            } else {
                employeeMstDto.setOfficePhone("");
            }
            if (null != employeeMstList1.getCellPhone() && employeeMstList1.getCellPhone() != 0) {
                employeeMstDto.setCellPhone(employeeMstList1.getCellPhone().toString());
            } else {
                employeeMstDto.setCellPhone("");
            }

            employeeMstDto.setEmployeeType(employeeMstList1.getEmpTypId().getTypeName());
            employeeMstDto.setEmployeeTypeId((employeeMstList1.getEmpTypId().getId().toString()));
            employeeMstDto.setDeactiveFlg(employeeMstList1.getDeactivateFlg());
            if (!employeeMstL.getEmpTypId().getTypeName().equalsIgnoreCase("Access and Authorization")) {
                employeeMstListDto.add(employeeMstDto);
            } else {
                if (!employeeMstList1.getEmpTypId().getTypeName().equalsIgnoreCase("Admin")) {
                    employeeMstListDto.add(employeeMstDto);
                }
            }

        }
        setListEmployee(employeeMstListDto);
        this.setFilteredEmployee(getListEmployee());

    }

    public void loadEmployeeListAll() {
        SimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MM-yyyy HH:mm");
        List<EmployeeMst> employeeMstList = employeeMstService.findAllNonDeletedEmployeeMsts();
        List<EmployeeMstDto> employeeMstListDto = new ArrayList<>();
        for (EmployeeMst employeeMstList1 : employeeMstList) {
            EmployeeMstDto employeeMstDto = new EmployeeMstDto();
            employeeMstDto.setId(employeeMstList1.getId().toString());
            employeeMstDto.setFirstName(employeeMstList1.getFirstName());
            employeeMstDto.setMidName(employeeMstList1.getMidName());
            employeeMstDto.setLastName(employeeMstList1.getLastName());
            employeeMstDto.setLoginId(employeeMstList1.getLoginId());
            employeeMstDto.setLoginPasswd(employeeMstList1.getLoginPasswd());
            if (employeeMstList1.getEmpDob() != null) {
                employeeMstDto.setEmpDob(sdf.format(employeeMstList1.getEmpDob()));
            } else {
                employeeMstDto.setEmpDob("");
            }
            employeeMstDto.setAddrsLine1(employeeMstList1.getAddrsLine1());
            employeeMstDto.setAddrsLine2(employeeMstList1.getAddrsLine2());
            employeeMstDto.setCity(employeeMstList1.getCity());
            employeeMstDto.setStateNm(employeeMstList1.getStateNm());
            if (null != employeeMstList1.getPin() && employeeMstList1.getPin() != 0) {
                employeeMstDto.setPin(employeeMstList1.getPin().toString());
            } else {
                employeeMstDto.setPin("");
            }
            employeeMstDto.setCountry(employeeMstList1.getCountry());
            employeeMstDto.setEmail(employeeMstList1.getEmail());
            if (null != employeeMstList1.getHomePhone() && employeeMstList1.getHomePhone() != 0) {
                employeeMstDto.setHomePhone(employeeMstList1.getHomePhone().toString());
            } else {
                employeeMstDto.setHomePhone("");
            }
            if (null != employeeMstList1.getOfficePhone() && employeeMstList1.getOfficePhone() != 0) {
                employeeMstDto.setOfficePhone(employeeMstList1.getOfficePhone().toString());
            } else {
                employeeMstDto.setOfficePhone("");
            }
            if (null != employeeMstList1.getCellPhone() && employeeMstList1.getCellPhone() != 0) {
                employeeMstDto.setCellPhone(employeeMstList1.getCellPhone().toString());
            } else {
                employeeMstDto.setCellPhone("");
            }

            employeeMstDto.setEmployeeType(employeeMstList1.getEmpTypId().getTypeName());
            employeeMstDto.setEmployeeTypeId((employeeMstList1.getEmpTypId().getId().toString()));
            employeeMstListDto.add(employeeMstDto);
        }
        setListEmployee(employeeMstListDto);
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public Long getEmpTypeId() {
        return empTypeId;
    }

    public void setEmpTypeId(Long empTypeId) {
        this.empTypeId = empTypeId;
    }

    public List<EmployeeMstDto> getFilteredEmployee() {
        return filteredEmployee;
    }

    public void setFilteredEmployee(List<EmployeeMstDto> filteredEmployee) {
        this.filteredEmployee = filteredEmployee;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
*/