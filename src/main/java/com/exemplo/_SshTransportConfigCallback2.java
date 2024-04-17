package com.exemplo;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.eclipse.jgit.api.TransportConfigCallback;
import org.eclipse.jgit.transport.*;
import org.eclipse.jgit.util.FS;

class _SshTransportConfigCallback2 implements TransportConfigCallback {

    private final SshSessionFactory sshSessionFactory;

    public _SshTransportConfigCallback2() {
        sshSessionFactory = new JschConfigSessionFactory() {

            @Override
            protected JSch getJSch(OpenSshConfig.Host hc, FS fs) throws JSchException {
                JSch jsch = super.getJSch(hc, fs);
                System.out.println("hc " + hc);
                jsch.removeAllIdentity();
                System.out.println("hc " + hc);
                jsch.addIdentity("~/.ssh/id_rsa");
                return jsch;
            }

            @Override
            protected void configure(final OpenSshConfig.Host hc, final Session session) {
                session.setConfig("StrictHostKeyChecking", "no");
            }
        };
    }

    @Override
    public void configure(final Transport transport) {
        SshTransport sshTransport = (SshTransport) transport;
        sshTransport.setSshSessionFactory(sshSessionFactory);
    }

}