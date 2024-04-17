 package com.exemplo;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.SshTransport;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Nova {

    public static void main(String[] args) throws JSchException {

        final SshSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
            @Override
            protected void configure(OpenSshConfig.Host hc, Session session) {
                session.setConfig("StrictHostKeyChecking", "no");
                //session.setConfig("UserKnownHostsFile", "~/.ssh");
                System.out.println("hc " + hc);

    
            }
        };


        String cloneUrl = "git@gitlab.com:jeanerufmg/pdfcount.git";
        String path = "/tmp/ssh";  // Altere para o caminho onde você deseja clonar o repositório
   
        
        try {
            
            Git.cloneRepository()
            .setURI(cloneUrl)
            .setDirectory(new java.io.File(path))
            .setTransportConfigCallback(transport -> {
                if (transport instanceof SshTransport) {
                  ((SshTransport) transport).setSshSessionFactory(sshSessionFactory);
                }
              })
            .call();
        System.out.println("Repositório clonado com sucesso em: " + path);
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }


}
