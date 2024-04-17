package com.exemplo;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import com.jcraft.jsch.JSchException;

public class GitCloneExample {

    public static void main(String[] args) throws JSchException {
        String cloneUrl = "git@gitlab.com:jeanerufmg/pdfcount.git";
        String path = "/tmp/ssh";  // Altere para o caminho onde você deseja clonar o repositório
        SshTransportConfigCallback sshTransport = new SshTransportConfigCallback();

        try {
            Git.cloneRepository()
            .setURI(cloneUrl)
            .setDirectory(new java.io.File(path))
            .setTransportConfigCallback(sshTransport)
            .call();
        System.out.println("Repositório clonado com sucesso em: " + path);
        } catch (GitAPIException e) {
            e.printStackTrace();
        }

     
}
}
