package com.exemplo;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.TransportConfigCallback;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.SshTransport;
import org.eclipse.jgit.transport.Transport;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.util.FS;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;

public class _GitCloneExampleC {

    public static void main(String[] args) {
        String cloneUrl = "git@gitlab.com:jeanerufmg/pdfcount.git";
        String path = "/tmp/ssh";  // Altere para o caminho onde você deseja clonar o repositório

        try {
            cloneRepository(cloneUrl, path);
        } catch (GitAPIException | JSchException e) {
            e.printStackTrace();
        }
    }

    public static void cloneRepository(String url, String path) throws GitAPIException, JSchException {
        final class SshTransportConfigCallback implements TransportConfigCallback {

            SshSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
                @Override
                protected void configure(OpenSshConfig.Host hc, Session session) {
                    session.setConfig("StrictHostKeyChecking", "no");
                }
            };
        
            @Override
            public void configure(Transport transport) {
                SshTransport sshTransport = (SshTransport) transport;
                sshTransport.setSshSessionFactory(sshSessionFactory);
            }

           
        }
      /*
        Git.cloneRepository()
            .setURI(url)
            .setDirectory(new java.io.File(path))
            .setTransportConfigCallback(transport -> {
                SshTransport sshTransport = (SshTransport) transport;
                sshTransport.setSshSessionFactory(sshSessionFactory);
            })
            .call();
        System.out.println("Repositório clonado com sucesso em: " + path);
        */
    };
    
    
}
