package com.exemplo;

import com.jcraft.jsch.Session;
import org.eclipse.jgit.api.TransportConfigCallback;
import org.eclipse.jgit.transport.*;

class SshTransportConfigCallback implements TransportConfigCallback {

    private final SshSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
        @Override
        protected void configure(OpenSshConfig.Host hc, Session session) {
            session.setConfig("StrictHostKeyChecking", "no");
            //session.setConfig("UserKnownHostsFile", "~/.ssh");
            System.out.println("hc " + hc);

        }
    };

    @Override
    public void configure(Transport transport) {
        SshTransport sshTransport = (SshTransport) transport;
        //sshTransport.setSshSessionFactory(sshSessionFactory);

        if (transport instanceof SshTransport) {
            ((SshTransport) transport).setSshSessionFactory(sshSessionFactory);
    }
}

}