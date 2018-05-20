/*package com.rank.futuregenerali.web;

import com.rank.futuregenerali.util.JavascriptCryptoDecryptor;
import com.rank.futuregenerali.dto.CallEmployeeMap;
import com.rank.futuregenerali.entities.CallDtl;
import com.rank.futuregenerali.entities.CallMst;
import com.rank.futuregenerali.entities.EmployeeActivityDtl;
import com.rank.futuregenerali.entities.EmployeeCallStatus;
import com.rank.futuregenerali.entities.EmployeeMst;
import com.rank.futuregenerali.entities.ForwardedCall;
import com.rank.futuregenerali.entities.ReasonMst;
import com.rank.futuregenerali.entities.TenancyEmployeeMap;
import com.rank.futuregenerali.service.CallDtlService;
import com.rank.futuregenerali.service.CallMstService;
import com.rank.futuregenerali.service.EmployeeActivityDtlService;
import com.rank.futuregenerali.service.EmployeeCallStatusService;
import com.rank.futuregenerali.service.EmployeeLoginService;
import com.rank.futuregenerali.service.EmployeeMstService;
import com.rank.futuregenerali.service.EmployeeTypeMstService;
import com.rank.futuregenerali.service.ForwardedCallService;
import com.rank.futuregenerali.service.ReasonMstService;
import com.rank.futuregenerali.service.TenancyEmployeeMapService;
import com.rank.futuregenerali.util.CallScheduler;
import com.rank.futuregenerali.util.Constants;
import com.rank.futuregenerali.util.CustomConvert;
import com.rank.futuregenerali.util.GenerateToken;
import com.rank.futuregenerali.util.SocketMessage;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class EmployeeLoginComponent implements Serializable {

    private static final long serialVersionUID = 6097515578842133912L;

    private static final Logger logger = Logger.getLogger(EmployeeLoginComponent.class);

    private String userLoginId;
    private String userPassword;
    private Boolean rememberMe;
    private String welcomeMessage;
    private boolean notAgent = true;
    private boolean adminmenuRenderer = true;
    private boolean supervisormenuRenderer = false;
    private String supervisorOragent;
    private EmployeeMst employeeMaster;
    private boolean logoutRenderer;
    private boolean reasonsRenderer;
    private Long selectedReasonId;
    private List<ReasonMst> reasonMstList;
    private String salt;
    private String four;

    @Autowired
    private EmployeeActivityDtlService employeeActivityDtlService;
    @Autowired
    private EmployeeMstService employeeMstService;
    @Autowired
    private EmployeeLoginService employeeLoginService;
    @Autowired
    private EmployeeTypeMstService employeeTypeMstService;
    @Autowired
    private ReasonMstService reasonMstService;
    @Autowired
    private TenancyEmployeeMapService tenancyEmployeeMapService;
    @Autowired
    private VidyoController vidyoAgent;
    @Autowired
    private EmployeeCallStatusService employeeCallStatusService;
    @Autowired
    private ForwardedCallService forwardedCallService;
    @Autowired
    private CallMstService callMstService;
    @Autowired
    private CallDtlService callDtlService;
    @Autowired
    CallScheduler callScheduler;
    @Autowired
    CustomerController customerComponent;

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public boolean isNotAgent() {
        return notAgent;
    }

    public void setNotAgent(boolean notAgent) {
        this.notAgent = notAgent;
    }

    public boolean getAdminmenuRenderer() {
        return adminmenuRenderer;
    }

    public void setAdminmenuRenderer(boolean adminmenuRenderer) {
        this.adminmenuRenderer = adminmenuRenderer;
    }

    public boolean getSupervisormenuRenderer() {
        return supervisormenuRenderer;
    }

    public void setSupervisormenuRenderer(boolean supervisormenuRenderer) {
        this.supervisormenuRenderer = supervisormenuRenderer;
    }

    public EmployeeMst getEmployeeMaster() {
        return employeeMaster;
    }

    public void setEmployeeMaster(EmployeeMst employeeMaster) {
        this.employeeMaster = employeeMaster;
    }

    public boolean getLogoutRenderer() {
        return logoutRenderer;
    }

    public void setLogoutRenderer(boolean logoutRenderer) {
        this.logoutRenderer = logoutRenderer;
    }

    public boolean getReasonsRenderer() {
        return reasonsRenderer;
    }

    public void setReasonsRenderer(boolean reasonsRenderer) {
        this.reasonsRenderer = reasonsRenderer;
    }

    public Long getSelectedReasonId() {
        return selectedReasonId;
    }

    public void setSelectedReasonId(Long selectedReasonId) {
        this.selectedReasonId = selectedReasonId;
    }

    public List<ReasonMst> getReasonMstList() {
        return reasonMstList;
    }

    public void setReasonMstList(List<ReasonMst> reasonMstList) {
        this.reasonMstList = reasonMstList;
    }

    public void showLoginReasons() {
        reasonsRenderer = true;
        reasonMstList = reasonMstService.findAllActivenNonDeletedReasonMsts("login");
    }

    public void showLogoutReasons() {
        reasonsRenderer = true;
    }

    public void cancelReasonList() {
        reasonsRenderer = false;
    }

    public String getSupervisorOragent() {
        return supervisorOragent;
    }

    public void setSupervisorOragent(String supervisorOragent) {
        this.supervisorOragent = supervisorOragent;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public String handleLogin(HttpServletRequest request) throws SQLException, IOException {

        String originalPass;
        try {
//            looger.info("decryptAESEncryptWithSaltAndIV(" + userPassword + "," + request.getSession().getId() + "," + salt + "," + four + ")");
            originalPass = JavascriptCryptoDecryptor.decryptAESEncryptWithSaltAndIV(userPassword, request.getSession().getId(), salt, four);

            logger.info("Original Password:" + originalPass);
            this.setUserPassword(originalPass);
        } catch (Exception ex) {
            logger.error("Unable to decrypt: " + ex.getMessage());
        }

        employeeMaster = employeeLoginService.checkLoginWithIdOnly(userLoginId, request.getSession().getId());
        
        if (null != employeeMaster) {
            if (!employeeMaster.getDeactivateFlg()) {
                employeeMaster = employeeLoginService.checkLogin(userLoginId, userPassword, userLoginId);
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry! username de-activated.", "Sorry! username de-activated."));
                return "/loginEmployee.xhtml";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown username.", "Unknown username."));
            return "/loginEmployee.xhtml";
        }

        RequestContext.getCurrentInstance().execute("document.getElementById('spinner').style.display='block';");

        vidyoAgent.setSocketHost(Constants.socketHostPublic);
        request.getSession().setAttribute("socketHostPublic", Constants.socketHostPublic);
        request.getSession().setAttribute("dockerUrl", Constants.dockerUrl);

//        employeeMaster = employeeLoginService.checkLogin(userLoginId, userPassword, request.getSession().getId());

        if (employeeMaster != null) {

            beforeEmployeeLoginCheck(employeeMaster);

            if (!CallScheduler.listCallEmp.isEmpty()) {
                for (CallEmployeeMap cem : CallScheduler.listCallEmp) {
                    if (employeeMaster != null && employeeMaster.getId() == cem.getEmployeeId().longValue()) {
                        CallScheduler.listCallEmp.remove(cem);
                        break;
                    }
                }
            }

            EmployeeMst empMst = employeeMstService.findEmployeeMstById(employeeMaster.getId());
            request.getSession().setAttribute("ormUserMaster", empMst);

//                    List<EmployeeTypeMst> empTypeMstAgentList = employeeTypeMstService.findActivenNonDeletedByProperty("typeName", "Underwriter Agent");
            notAgent = false;
//                    EmployeeTypeMst employeeTypeAgentMst;
            reasonMstList = reasonMstService.findAllActivenNonDeletedReasonMsts("logout");
            if (!reasonMstList.isEmpty()) {
                selectedReasonId = reasonMstList.get(0).getId();
            }

//                    if (null == empTypeMstAgentList || empTypeMstAgentList.isEmpty()) {
//                        logger.info("L1 ~ Sorry! Employee Type of Name 'Underwriter Agent' Not Found in the Database.");
//                        FacesContext.getCurrentInstance().addMessage(null,
//                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry! Employee Type of Name 'Underwriter Agent' Not Found in the Database.", "Employee Type of Name 'Agent' Not Found in the Database."));
//                        return "/loginEmployee.xhtml";
//                    } 
//                    else {
//                        employeeTypeAgentMst = empTypeMstAgentList.get(0);
//                        if (null != employeeMaster.getEmpTypId().getId()) {
//                            // Employee Is Valid and Is Eligible to Login In
//                            if (Objects.equals(employeeMaster.getEmpTypId().getId(), employeeTypeAgentMst.getId())) {
//                                //First delete all the old Call Proficiencies from Database then insert again for fresh values
//                                Integer lReturn = employeeLoginService.deleteAndRePopulateCallProficiencies(employeeMaster, employeeTypeAgentMst);
//                                if (lReturn != 0) {
//                                    logger.info("EmpId:" + employeeMaster.getId() + " LoginId:" + employeeMaster.getLoginId() + " is Available in the Agent Call Routing Process.");
//                                    //  List<EmployeeCallProficiency> prof = employeeCallProficiencyService.findByPrimaryCategoryVideorText(employeeMaster.getId());
//
//                                } else {
//                                    logger.info("L2 ~ EmpId:" + employeeMaster.getId() + " LoginId:" + employeeMaster.getLoginId() + " has incomplete Proficiencies. Thus not available in Agent Routing Process.");
//                                    System.err.println("ERROR: ==> L2 ~ EmpId:" + employeeMaster.getId() + " LoginId:" + employeeMaster.getLoginId() + " has incomplete Proficiencies. Thus not available in Agent Routing Process.");
//                                    FacesContext.getCurrentInstance().addMessage(null,
//                                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Not in Agent Routing. Contact your Supervisor.", "Your Call Proficiencies are incomplete. Thus not available in Call Routing Process."));
//                                    return "/loginEmployee.xhtml";
//                                }
//                            }
//                        } else {
//
//                            logger.info("L5 ~ Sorry! Employee Type Id Not found in the Database for EmpId:" + employeeMaster.getId() + " LoginId:" + employeeMaster.getLoginId());
//                            FacesContext.getCurrentInstance().addMessage(null,
//                                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry! Employee Type Id Not found", "Employee Type Id Not found in the Database for EmpId:" + employeeMaster.getId() + " LoginId:" + employeeMaster.getLoginId()));
//                            return "/loginEmployee.xhtml";
//                        }
//                    }
            // ===========> Insert the Activity Detail ===============
            //List<ReasonMst> lReasonMstList = reasonMstService.findReasonMstByReasonCode("LN001");
            ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("LN001");
            if (null == reasonMst) {
                reasonMst = new ReasonMst();
                reasonMst.setType("login");
                reasonMst.setReasonCd("LN001");
                reasonMst.setReasonDesc("Login into application");
                reasonMst.setActiveFlg(true);
                reasonMst.setDeleteFlg(false);
                reasonMst = reasonMstService.save(reasonMst, employeeMaster);

            }
            EmployeeActivityDtl employeeActivityDtl = new EmployeeActivityDtl();
            employeeActivityDtl.setEmpId(employeeMaster);
            employeeActivityDtl.setActivity("login");
            try {
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
            } catch (ParseException e) {
                logger.info("Error:" + e.getMessage());
            }
            employeeActivityDtl.setReasonId(reasonMst);
            employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
            employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());
            employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl, empMst);

            //--------------------------------Added To handle Second login-----------------------------
            request.getSession().setAttribute("currentEmpLoginId", employeeActivityDtl);

            // If Activity Details Failed
            if (null == employeeActivityDtl) {
                logger.info("L4 ~ Sorry! Unable to add activity details for EmpId:" + employeeMaster.getId() + " LoginId:" + employeeMaster.getLoginId());
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Failed! Activity Not Populated.", "Failed! Unable to add activity details."));
                return "/loginEmployee.xhtml";
            } else {
                //TODO: Get the Roles and Permission Maps of the Employee.

                request.getSession().setAttribute("userFNameLName", ((employeeMaster.getFirstName() == null || employeeMaster.getFirstName().trim().length() <= 0) ? "User" : (employeeMaster.getFirstName() + ((employeeMaster.getLastName() == null || employeeMaster.getLastName().trim().length() <= 0) ? "" : ((employeeMaster.getMidName() == null || employeeMaster.getMidName().trim().length() <= 0) ? "" : " " + employeeMaster.getMidName()) + " " + employeeMaster.getLastName()))));
                request.getSession().setAttribute("loginstatus", "true");
                System.out.println("======type Name===" + employeeMaster.getEmpTypId().getTypeName());
                if (employeeMaster.getEmpTypId().getTypeName().equalsIgnoreCase("Admin")) {
                    welcomeMessage = "WelCome to the Future Generli Admin Console";
                    request.getSession().setAttribute("ormAdminMaster", employeeMaster);
                    logoutRenderer = true;
                    notAgent = true;
                    supervisormenuRenderer = false;
                    adminmenuRenderer = true;

                    return "/pages/employee/dashboard.xhtml?faces-redirect=true";
                } else if (employeeMaster.getEmpTypId().getTypeName().equalsIgnoreCase("Underwriter Agent")) {
                    welcomeMessage = "";
                    logoutRenderer = true;
                    notAgent = true;
                    supervisormenuRenderer = false;
                    adminmenuRenderer = false;

                    employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl, empMst);
                    request.getSession().setAttribute("currentEmpLoginId", employeeActivityDtl);
                    System.err.println("=========> In Login: currentEmpLoginId : " + employeeActivityDtl.toString());
                    this.supervisorOragent = "agent";
                    EmployeeMst findEmployeeMstById = employeeMstService.findEmployeeMstById(employeeMaster.getId());
                    Long number = (long) Math.floor(Math.random() * 9000000L) + 1000000L;
                    String room = userLoginId + number;
                    vidyoAgent.setSelfviewenabled(false);
                    vidyoAgent.setSettingsenabled(true);
                    vidyoAgent.setHangupenabled(true);
                    vidyoAgent.setHomeButtonEnabled(false);
                    vidyoAgent.setSettingsenabled(true);
                    vidyoAgent.setAvailableenabled(true);
                    vidyoAgent.setDialenabled(true);
                    vidyoAgent.setHoldenabled(true);
                    vidyoAgent.setThreewayenabled(true);
                    vidyoAgent.setForwardenabled(true);
                    vidyoAgent.setNotreadyenabled(true);
                    vidyoAgent.setLogoutenabled(false);
                    vidyoAgent.setRoomName(room);
                    vidyoAgent.setRecordButtonEnabled(false);
                    String token = GenerateToken.getToken();
                    vidyoAgent.setRoomId(token);

                    TenancyEmployeeMap tenancyEmployeeMap = new TenancyEmployeeMap();
                    tenancyEmployeeMap.setEmpId(findEmployeeMstById);
                    tenancyEmployeeMap.setRoomLink(token);
                    tenancyEmployeeMap.setRoomName(room);
                    vidyoAgent.setAgentName((employeeMaster.getFirstName() == null || employeeMaster.getFirstName().trim().length() <= 0) ? "User" : (employeeMaster.getFirstName() + ((employeeMaster.getLastName() == null || employeeMaster.getLastName().trim().length() <= 0) ? "" : ((employeeMaster.getMidName() == null || employeeMaster.getMidName().trim().length() <= 0) ? "" : " " + employeeMaster.getMidName()) + " " + employeeMaster.getLastName())) + "~" + this.userLoginId);

                    tenancyEmployeeMapService.saveTenancyEmployeeMap(tenancyEmployeeMap);

                    request.getSession().setAttribute("tenancyEmployeeMap", tenancyEmployeeMap);

                    request.getSession().setAttribute("ormAgentMaster", employeeMaster);

                    List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(employeeMaster.getId());
                    request.getSession().setAttribute("logincount", tenancyEmployeeMaplist.size());
                    logoutRenderer = false;

                    EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                    List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(employeeMaster);

                    if (!empClStatusList.isEmpty()) {
                        for (EmployeeCallStatus empstatus : empClStatusList) {
                            empCallStatus = empstatus;
                        }

                        empCallStatus.setStatus(false);
                        empCallStatus.setReadyTime(null);
                        try {
                            empCallStatus.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        } catch (ParseException ex) {
                            java.util.logging.Logger.getLogger(EmployeeLoginComponent.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                    }
                    return "/pages/agent/underwriterDashboard.xhtml?faces-redirect=true";

                } else if (employeeMaster.getEmpTypId().getTypeName().equalsIgnoreCase("Customer Care Agent")) {
                    welcomeMessage = "";
                    logoutRenderer = true;
                    notAgent = true;
                    supervisormenuRenderer = false;
                    adminmenuRenderer = false;

                    employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl, empMst);
                    request.getSession().setAttribute("currentEmpLoginId", employeeActivityDtl);
                    System.err.println("=========> In Login: currentEmpLoginId : " + employeeActivityDtl.toString());
                    this.supervisorOragent = "agent";
                    EmployeeMst findEmployeeMstById = employeeMstService.findEmployeeMstById(employeeMaster.getId());
                    Long number = (long) Math.floor(Math.random() * 9000000L) + 1000000L;
                    String room = userLoginId + number;
                    vidyoAgent.setSelfviewenabled(false);
                    vidyoAgent.setSettingsenabled(true);
                    vidyoAgent.setHangupenabled(true);
                    vidyoAgent.setHomeButtonEnabled(false);
                    vidyoAgent.setSettingsenabled(true);
                    vidyoAgent.setAvailableenabled(true);
                    vidyoAgent.setDialenabled(true);
                    vidyoAgent.setHoldenabled(true);
                    vidyoAgent.setThreewayenabled(true);
                    vidyoAgent.setForwardenabled(true);
                    vidyoAgent.setNotreadyenabled(true);
                    vidyoAgent.setLogoutenabled(false);
                    vidyoAgent.setRoomName(room);
                    String token = GenerateToken.getToken();
                    vidyoAgent.setRoomId(token);
                    
                 
                    

                    TenancyEmployeeMap tenancyEmployeeMap = new TenancyEmployeeMap();
                    tenancyEmployeeMap.setEmpId(findEmployeeMstById);
                    tenancyEmployeeMap.setRoomLink(token);
                    tenancyEmployeeMap.setRoomName(room);
                    vidyoAgent.setAgentName((employeeMaster.getFirstName() == null || employeeMaster.getFirstName().trim().length() <= 0) ? "User" : (employeeMaster.getFirstName() + ((employeeMaster.getLastName() == null || employeeMaster.getLastName().trim().length() <= 0) ? "" : ((employeeMaster.getMidName() == null || employeeMaster.getMidName().trim().length() <= 0) ? "" : " " + employeeMaster.getMidName()) + " " + employeeMaster.getLastName())) + "~" + this.userLoginId);

                    tenancyEmployeeMapService.saveTenancyEmployeeMap(tenancyEmployeeMap);

                    request.getSession().setAttribute("tenancyEmployeeMap", tenancyEmployeeMap);

                    request.getSession().setAttribute("ormAgentMaster", employeeMaster);

                    List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(employeeMaster.getId());
                    request.getSession().setAttribute("logincount", tenancyEmployeeMaplist.size());
                    logoutRenderer = false;

                    EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                    List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(employeeMaster);

                    if (!empClStatusList.isEmpty()) {
                        for (EmployeeCallStatus empstatus : empClStatusList) {
                            empCallStatus = empstatus;
                        }

                        empCallStatus.setStatus(false);
                        empCallStatus.setReadyTime(null);
                        try {
                            empCallStatus.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        } catch (ParseException ex) {
                            java.util.logging.Logger.getLogger(EmployeeLoginComponent.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                    }
                    return "/pages/agent/customerCareAgentDashboard.xhtml?faces-redirect=true";

                } else {
                    try {

                        EmployeeMst findEmployeeMstById = employeeMstService.findEmployeeMstById(employeeMaster.getId());
                        Long number = (long) Math.floor(Math.random() * 9000000L) + 1000000L;
                        String room = userLoginId + number;
                        vidyoAgent.setSelfviewenabled(false);
                        vidyoAgent.setSettingsenabled(true);
                        vidyoAgent.setHangupenabled(true);
                        vidyoAgent.setHomeButtonEnabled(false);
                        vidyoAgent.setSettingsenabled(true);
                        vidyoAgent.setAvailableenabled(true);
                        vidyoAgent.setDialenabled(true);
                        vidyoAgent.setHoldenabled(true);
                        vidyoAgent.setThreewayenabled(true);
                        vidyoAgent.setForwardenabled(true);
                        vidyoAgent.setNotreadyenabled(true);
                        vidyoAgent.setLogoutenabled(false);
                        vidyoAgent.setRoomName(room);
                        String token = GenerateToken.getToken();
                        vidyoAgent.setRoomId(token);

                        TenancyEmployeeMap tenancyEmployeeMap = new TenancyEmployeeMap();
                        tenancyEmployeeMap.setEmpId(findEmployeeMstById);
                        tenancyEmployeeMap.setRoomLink(token);
                        tenancyEmployeeMap.setRoomName(room);
                        vidyoAgent.setAgentName((employeeMaster.getFirstName() == null || employeeMaster.getFirstName().trim().length() <= 0) ? "User" : (employeeMaster.getFirstName() + ((employeeMaster.getLastName() == null || employeeMaster.getLastName().trim().length() <= 0) ? "" : ((employeeMaster.getMidName() == null || employeeMaster.getMidName().trim().length() <= 0) ? "" : " " + employeeMaster.getMidName()) + " " + employeeMaster.getLastName())) + "~" + this.userLoginId);

                        tenancyEmployeeMapService.saveTenancyEmployeeMap(tenancyEmployeeMap);

                        request.getSession().setAttribute("tenancyEmployeeMap", tenancyEmployeeMap);

                        employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl, empMst);

                        //--------------------------------Added To handle Second login-----------------------------
                        request.getSession().setAttribute("currentEmpLoginId", employeeActivityDtl);
                        System.err.println("=========> In Login: currentEmpLoginId : " + employeeActivityDtl.toString());

                        request.getSession().setAttribute("ormAgentMaster", employeeMaster);

                        List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(employeeMaster.getId());
                        request.getSession().setAttribute("logincount", tenancyEmployeeMaplist.size());
                        logoutRenderer = false;

                        EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                        List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(employeeMaster);

                        if (!empClStatusList.isEmpty()) {
                            for (EmployeeCallStatus empstatus : empClStatusList) {
                                empCallStatus = empstatus;
                            }

                            empCallStatus.setStatus(false);
                            empCallStatus.setReadyTime(null);
                            empCallStatus.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                        }
                        return "/pages/agent/salesAgentDashboard.xhtml?faces-redirect=true";

                    } catch (ParseException e) {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry! Unable to get into Vidyo Portal", ""));

                        request.getSession().setAttribute("ormAgentMaster", null);
                        request.getSession().setAttribute("ormAdminMaster", null);
                        return "/loginEmployee.xhtml?faces-redirect=true";
                    }
                }
            }
        } else {
            logger.info("L7 ~ Failed! Wrong Login Credentials.");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong Password.", "Failed! Wrong Password."));
            return "/loginEmployee.xhtml";
        }

    }

   

    public void browserClosed() {

        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
        EmployeeMst empMaster = (EmployeeMst) session.getAttribute("ormUserMaster");
        beforeEmployeeLoginCheck(empMaster);
        sessionhandleLogout((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        logger.info("End Browser Closed....");

    }

    public void beforeEmployeeLoginCheck(EmployeeMst emst) {
    	
    	logger.info("beforeEmployeeLoginCheck beforeEmployeeLoginCheck :"+emst);

        if (emst != null) {
            vidyoAgent.setDashboardCheck(false);
            vidyoAgent.setSetupCheck(true);
            vidyoAgent.setHangupenabled(true);
            vidyoAgent.setHomeButtonEnabled(false);
            vidyoAgent.setSettingsenabled(true);
            vidyoAgent.setAvailableenabled(false);
            vidyoAgent.setAgentStatus("Ready");
            vidyoAgent.setAgentColor("");
            vidyoAgent.setDialenabled(true);
            vidyoAgent.setThreewayenabled(true);
            vidyoAgent.setForwardenabled(true);
            vidyoAgent.setNotreadyenabled(true);
            vidyoAgent.setRecordButtonEnabled(false);
            try {

                Date date = new Date();

                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "login");
                Long resLoginId = Long.parseLong("0");
                ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("SOUT");
                if (null == reasonMst) {
                    reasonMst = new ReasonMst();
                    reasonMst.setType("Logout");
                    reasonMst.setReasonCd("SOUT");
                    reasonMst.setReasonDesc("Session time over, idle timeout.");
                    reasonMst.setActiveFlg(true);
                    reasonMst.setDeleteFlg(false);
                    reasonMst = reasonMstService.save(reasonMst, employeeMaster);
                }
                if (null != employeeActivityDtl) {
                    resLoginId = employeeActivityDtl.getId();
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl, emst);
                }

                employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setEmpId(emst);
                employeeActivityDtl.setActivity("logout");
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(date));
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                employeeActivityDtl.setRespectiveLoginId(resLoginId);
                employeeActivityDtl.setReasonId(reasonMst);
                employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
                employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());
                employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl, emst);

                if (null != employeeActivityDtl) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtl.setNotification(null);
                    employeeActivityDtlService.save(employeeActivityDtl, emst);

                }
                
                

             employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(emst.getId(), "Self View");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(emst.getId(), "not ready");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(emst.getId(), "NEXT CALL");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(emst.getId(), "Call Started");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(emst.getId(), "Hang Up");

                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                // Update the Call Status to Zero
                List<EmployeeCallStatus> empCallStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(emst);
                for (EmployeeCallStatus callStatus : empCallStatusList) {
                    //callStatus.setCallCount(0);
                    callStatus.setStatus(false);
                    callStatus.setReadyTime(null);
                    employeeCallStatusService.saveEmployeeCallStatus(callStatus);
                }

                //Update the Foward Call Active Flag to Zero
                List<ForwardedCall> forwardedCallList = forwardedCallService.findActiveForwardedCallByEmployeeMstList(emst);
                for (ForwardedCall forwardedCall : forwardedCallList) {
                    forwardedCall.setActiveFlg(false);
                    forwardedCall.setDeleteFlg(true);
                    forwardedCallService.saveForwardedCalls(forwardedCall, emst);
                }

                logger.info(
                        ">>>>Logout Successfully>>> Employee UserId: " + emst.getLoginId()
                        + " Employee Name:" + emst.getFirstName() + " " + employeeMaster.getMidName() + " " + emst.getLastName() + ".");

            } catch (ParseException ex) {

                logger.error("Deletion of User Connection Data Failed, though Logging Out. ", ex);
            }
        } else {
            logger.debug(">>>>Logout Successfully, but NO User Details found in this session, OR is requesting again after Logout.");
        }
    }

    public String sessionhandleLogout(HttpServletRequest request) {

        logger.info("Inside sessionhandleLogout......");
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
        CallMst cm = (CallMst) session.getAttribute("callMst");

        employeeMaster = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        if (cm != null) {
            cm = callMstService.findCallMstById(cm.getId());
            List<EmployeeCallStatus> employeeCallStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(employeeMaster);
            for (EmployeeCallStatus employeeCallStatusList1 : employeeCallStatusList) {
                employeeCallStatusList1.setStatus(false);
                employeeCallStatusList1.setReadyTime(null);
                employeeCallStatusService.saveEmployeeCallStatus(employeeCallStatusList1);
            }
            try {
                cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callMstService.saveCallMst(cm);
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(EmployeeLoginComponent.class.getName()).log(Level.SEVERE, null, ex);
            }

            vidyoAgent.showhangUpReasons(request);
            vidyoAgent.setHangupcalled(false);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(EmployeeLoginComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestContext.getCurrentInstance().execute("chatSave();");

        }
        vidyoAgent.setDashboardCheck(false);
        vidyoAgent.setSetupCheck(true);
        session.setAttribute("callMstChat", cm);
        session.setAttribute("ormAgentMaster", null);
        session.setAttribute("ormAdminMaster", null);
        session.setAttribute("ormUserMaster", null);

        session.setAttribute("callMst", null);
        session.setAttribute("loginstatus", "false");
        session.setAttribute("userFNameLName", "");

        employeeMaster = null;
        reasonsRenderer = false;
        vidyoAgent.setHangupcalled(true);
        adminmenuRenderer = false;
        supervisormenuRenderer = false;

        return "/loginEmployee.xhtml?faces-redirect=true";
    }

    public String sessionhandleLogoutAgent(HttpServletRequest request) {

        logger.info("Inside sessionhandleLogoutAgent......");
        CallMst clMst = (CallMst) request.getSession().getAttribute("callMst");
        if (null != clMst) {
            clMst = callMstService.findCallMstById(clMst.getId());
        }
        EmployeeMst employeeMasterL = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");
        try {
            if (null != employeeMasterL) {
                Date date = new Date();

                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "login");
                Long resLoginId = Long.parseLong("0");
                ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("SOUT");
                if (null == reasonMst) {
                    reasonMst = new ReasonMst();
                    reasonMst.setType("Logout");
                    reasonMst.setReasonCd("SOUT");
                    reasonMst.setReasonDesc("Session time over, idle timeout.");
                    reasonMst.setActiveFlg(true);
                    reasonMst.setDeleteFlg(false);
                    reasonMst = reasonMstService.save(reasonMst, employeeMasterL);
                }
                if (null != employeeActivityDtl) {
                    resLoginId = employeeActivityDtl.getId();
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl, employeeMasterL);
                }

                employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setEmpId(employeeMasterL);
                employeeActivityDtl.setActivity("logout");
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(date));
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                employeeActivityDtl.setRespectiveLoginId(resLoginId);
                employeeActivityDtl.setReasonId(reasonMst);
                employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
                employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());

                employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl, employeeMasterL);

                if (null != employeeActivityDtl) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtl.setNotification(null);
                    employeeActivityDtlService.save(employeeActivityDtl, employeeMasterL);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMasterL.getId(), "Self View");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);
                    vidyoAgent.setSelfviewenabled(false);
                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMasterL.getId(), "not ready");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMasterL.getId(), "NEXT CALL");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMasterL.getId(), "Call Started");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMasterL.getId(), "Hang Up");

                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                // Update the Call Status to Zero
                List<EmployeeCallStatus> empCallStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(employeeMasterL);
                for (EmployeeCallStatus callStatus : empCallStatusList) {
                    //callStatus.setCallCount(0);
                    callStatus.setStatus(false);
                    callStatus.setReadyTime(null);
                    employeeCallStatusService.saveEmployeeCallStatus(callStatus);
                }

            }

        } catch (ParseException ex) {

            logger.error("sessionhandleLogoutAgent Parse:" + ex.getMessage());
        }
        if (null == clMst) {

        } else {

            if (clMst.getEndTime() == null) {
                vidyoAgent.setAgentStatus("Ready");
                vidyoAgent.setAgentColor("");
                vidyoAgent.setHangupenabled(true);

                vidyoAgent.setHomeButtonEnabled(false);
                vidyoAgent.setSettingsenabled(true);
                vidyoAgent.setAvailableenabled(false);
                vidyoAgent.setDialenabled(true);
                vidyoAgent.setHoldenabled(true);
                vidyoAgent.setThreewayenabled(true);
                vidyoAgent.setForwardedcall(true);
                vidyoAgent.setNotreadyenabled(true);
                vidyoAgent.setLogoutenabled(false);
                vidyoAgent.setRecordingStatus("");

                vidyoAgent.setIworkImage(false);
                vidyoAgent.setFilepanel(false);
                vidyoAgent.setSelfviewenabled(false);
//                vidyoAgent.setDashboardCheck(true);
//                vidyoAgent.setSetupCheck(false);
                try {
                    if (null != employeeMaster) {
                        CallDtl callDtl_L = callDtlService.findCallDtlByCallMasterIdAndAgentNotForwarded(clMst.getId(), employeeMaster.getId());
                        if (null != callDtl_L && !callDtl_L.getCallTypeInfo().equalsIgnoreCase("ThreeWay")) {
                            if (!clMst.getCallMedium().equalsIgnoreCase("Web")) {

                                try {
                                    logger.info("Send hang Up to Mobile............");
                                    String callType = "incoming_call";
                                    if (clMst.getCallType().equalsIgnoreCase("Incoming Call")) {
                                        callType = "incoming_call";
                                    }
                                    if (clMst.getCallType().equalsIgnoreCase("Schedule")) {
                                        callType = "schedule_call";
                                    }

                                    SocketMessage.send(callScheduler.getAdminSocket(), clMst.getCustId(), callType + "#hangUp#" + clMst.getId());

                                } catch (Exception ex) {
                                    logger.error("Exception sessionhandleLogoutAgent" + ex.getMessage());
                                }

                            }
                        }
                        List<CallDtl> clDtlList = callDtlService.findCallDtlByCallMasterIdAndAgent(clMst.getId(), employeeMaster.getId());
                        if (clDtlList.isEmpty()) {
                            // Nothing to Update
                        } else {
                            for (CallDtl clDtl : clDtlList) {
                                if (!clDtl.getCallTypeInfo().equalsIgnoreCase("Threeway")) {
                                    clMst.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    clMst.setCallStatus("Ended");
                                }

                                clMst = callMstService.saveCallMst(clMst);
                                clDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                clDtl.setAgentComments("Call Abandoned due to System Close.");
                                callDtlService.saveCallDtl(clDtl);
                            }
                        }
                    }
                } catch (ParseException ex) {
                    logger.error("sessionhandleLogoutAgent:" + ex.getMessage());
                } finally {
                    request.getSession().setAttribute("callMst", null);
                    request.getSession().setAttribute("previousCallMst", null);
                }
            }
        }

        return "/loginEmployee.xhtml?faces-redirect=true";
    }

    public String handleLogout(HttpServletRequest request) {

        employeeMaster = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        ReasonMst reasonMst = reasonMstService.findNonDeletedById(selectedReasonId);
        if (employeeMaster != null) {
            List<EmployeeCallStatus> employeeCallStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(employeeMaster);
            for (EmployeeCallStatus employeeCallStatusList1 : employeeCallStatusList) {
                employeeCallStatusList1.setStatus(false);
                employeeCallStatusList1.setReadyTime(null);
                employeeCallStatusService.saveEmployeeCallStatus(employeeCallStatusList1);
            }
            try {

                Date date = new Date();
                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "login");
                Long resLoginId = Long.parseLong("0");
                if (null != employeeActivityDtl) {
                    resLoginId = employeeActivityDtl.getId();
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl, employeeMaster);
                }

                employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setEmpId(employeeMaster);
                employeeActivityDtl.setActivity("logout");
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(date));
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                employeeActivityDtl.setRespectiveLoginId(resLoginId);
                employeeActivityDtl.setReasonId(reasonMst);
                employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
                employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());
                employeeActivityDtlService.save(employeeActivityDtl, employeeMaster);
                List<TenancyEmployeeMap> tenancyEmployeeMaplst = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(employeeMaster.getId());
                for (TenancyEmployeeMap tenancyEmployeeMaplst1 : tenancyEmployeeMaplst) {
                    tenancyEmployeeMapService.deleteTenancyEmployeeMap(tenancyEmployeeMaplst1);
                }

                logger.info(
                        ">>>>Logout Successfully>>> Employee UserId: " + employeeMaster.getLoginId()
                        + " Employee Name:" + employeeMaster.getFirstName() + " " + employeeMaster.getMidName() + " " + employeeMaster.getLastName() + ".");

            } catch (ParseException ex) {
                RequestContext.getCurrentInstance().execute(" $('#spinner').hide();");

                logger.error("Deletion of User Connection Data Failed, though Logging Out. ", ex);
            }
        } else {
            logger.debug(">>>>Logout Successfully, but NO User Details found in this session, OR is requesting again after Logout.");
        }
        request.getSession().invalidate();
        request.getSession(true);
        request.getSession().setAttribute("callMst", null);
        request.getSession().setAttribute("previousCallMst", null);
        logger.info("8: \"callMst\", null");
        request.getSession().setAttribute("loginstatus", "false");
        request.getSession().setAttribute("userFNameLName", "");
        request.getSession().setAttribute("ormAgentMaster", null);
        request.getSession().setAttribute("ormAdminMaster", null);
        request.getSession().setAttribute("ormUserMaster", null);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Logged Out!", "User Logged Out Successfully."));
        RequestContext.getCurrentInstance().execute(" $('#spinner').hide();");
        employeeMaster = null;
        adminmenuRenderer = false;
        supervisormenuRenderer = false;

        return "/loginEmployee.xhtml?faces-redirect=true";
    }

    public String leave() {
        return "/loginEmployee.xhtml?faces-redirect=true";
    }

}
*/