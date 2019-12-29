package com.fictiongym.helper;

import java.util.ArrayList;

public class MemberHelper {

    public static String generateTempUsername(String firstName,String lastName){
        String currMillSec = String.valueOf(System.currentTimeMillis());

        return firstName.substring(0,1) + "_" + lastName + "_"
                + currMillSec.substring(currMillSec.length() - 4,currMillSec.length() - 1);
    }

    public static String generateTempRandomPassword(){
        return "$!" + String.valueOf((int)(Math.random() * 10000));
    }

    public static boolean isUserStaffMember(String role){
        ArrayList<String> list = new ArrayList();

        list.add("ROLE_MANAGER");
        list.add("ROLE_SALES_MANAGER");
        list.add("ROLE_SALES_PERSON");
        list.add("ROLE_TRAINER");
        list.add("ROLE_GEN_WORKER");
        list.add("ROLE_RECEPTIONIST");

        return list.contains(role);
    }

    public static String getStaffMemberRoleCode(String roleDesc){
        roleDesc = roleDesc.trim();
        String systemRole = "";

        if (roleDesc.equals("Manager")){
            systemRole = "ROLE_MANAGER";
        }
        else if (roleDesc.equals("Sales Manager")){
            systemRole = "ROLE_SALES_MANAGER";
        }
        else if (roleDesc.equals("Instructor")){
            systemRole = "ROLE_TRAINER";
        }
        else if (roleDesc.equals("General Worker")){
            systemRole = "ROLE_GEN_WORKER";
        }
        else if (roleDesc.equals("Sales Person")){
            systemRole = "ROLE_SALES_PERSON";
        }
        else if (roleDesc.equals("Receptionist")){
            systemRole = "ROLE_RECEPTIONIST";
        }
        return systemRole;
    }

}
