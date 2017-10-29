package org.jitesh.examples.restapispringboot.filter;

import java.io.ByteArrayInputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

public class BufferedServletInputStream extends ServletInputStream{
    private ByteArrayInputStream bais;

    public BufferedServletInputStream(ByteArrayInputStream bais) {
        this.bais = bais;
    }

    @Override
    public int available() {
        return this.bais.available();
    }

    @Override
    public int read() {
        return this.bais.read();
    }

    @Override
    public int read(byte[] buf, int off, int len) {
        return this.bais.read(buf, off, len);
    }

    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isReady() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        // TODO Auto-generated method stub
        
    }
}
