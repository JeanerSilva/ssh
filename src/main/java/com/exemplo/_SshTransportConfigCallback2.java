package com.exemplo;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.eclipse.jgit.api.TransportConfigCallback;
import org.eclipse.jgit.transport.*;
import org.eclipse.jgit.util.FS;

public class _SshTransportConfigCallback2 implements TransportConfigCallback {

    public static final _SshTransportConfigCallback2 INSTANCE = new _SshTransportConfigCallback2();
    private final SshSessionFactory sshSessionFactory = new JschConfigSessionFactory() {

        @Override

        protected void configure(OpenSshConfig.Host hc, Session session) {
            session.setConfig("StrictHostKeyChecking", "no");
        }

        @Override

        protected JSch createDefaultJSch(FS fs) throws JSchException {

            try {
                JSch defaultJSch = super.createDefaultJSch(fs);
                defaultJSch.addIdentity("~/.ssh/id_rsa");
                return defaultJSch;
            } catch (Exception e) {
                throw new JSchException(e.getMessage(), e);
            }

        }

    };

    public void configure(Transport transport) {
        SshTransport sshTransport = (SshTransport) transport;
        sshTransport.setSshSessionFactory(sshSessionFactory);
    }


}