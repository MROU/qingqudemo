package com.ouyang.qingque.model;

import java.io.Serializable;

/**
 * Created by fengyun on 2018/3/27.
 */

public class NoteAtString implements Serializable {
    private int atConcernCount;

    public int getAtConcernCount() {
        return atConcernCount;
    }

    public void setAtConcernCount(int atConcernCount) {
        this.atConcernCount = atConcernCount;
    }

    public int getCanSendPrivMsg() {
        return canSendPrivMsg;
    }

    public void setCanSendPrivMsg(int canSendPrivMsg) {
        this.canSendPrivMsg = canSendPrivMsg;
    }

    public String[] getCommiteeClasses() {
        return commiteeClasses;
    }

    public void setCommiteeClasses(String[] commiteeClasses) {
        this.commiteeClasses = commiteeClasses;
    }

    public FirstAtConcernUser getFirstAtConcernUser() {
        return firstAtConcernUser;
    }

    public void setFirstAtConcernUser(FirstAtConcernUser firstAtConcernUser) {
        this.firstAtConcernUser = firstAtConcernUser;
    }

    private int canSendPrivMsg;
    private String[] commiteeClasses;
    private FirstAtConcernUser firstAtConcernUser;

    public class FirstAtConcernUser{
        public String getUserIcon() {
            return userIcon;
        }

        public void setUserIcon(String userIcon) {
            this.userIcon = userIcon;
        }

        public String getUserNick() {
            return userNick;
        }

        public void setUserNick(String userNick) {
            this.userNick = userNick;
        }

        public int getHonor() {
            return honor;
        }

        public void setHonor(int honor) {
            this.honor = honor;
        }

        public ShowRole getShowRole() {
            return showRole;
        }

        public void setShowRole(ShowRole showRole) {
            this.showRole = showRole;
        }

        private String userIcon;
        private String userNick;
        private int honor;
        private ShowRole showRole;
        public class ShowRole{
            public int getRoleId() {
                return roleId;
            }

            public void setRoleId(int roleId) {
                this.roleId = roleId;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            private int roleId;
            private String roleName;
        }
    }

}
