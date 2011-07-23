package org.msgpack.template;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.TestSet;
import org.msgpack.packer.BufferPacker;
import org.msgpack.packer.StreamPacker;
import org.msgpack.unpacker.BufferUnpacker;
import org.msgpack.unpacker.StreamUnpacker;


public class TestBigIntegerTemplate {

    @Test
    public void testStreamPackStreamUnpack() throws Exception {
	new TestStreamPackStreamUnpack().testBigInteger();
    }

    @Test
    public void testStreamPackBufferUnpack() throws Exception {
	new TestStreamPackBufferUnpack().testBigInteger();
    }

    @Test
    public void testBufferPackBufferUnpack() throws Exception {
	new TestBufferPackBufferUnpack().testBigInteger();
    }

    @Test
    public void testBufferPackStreamUnpack() throws Exception {
	new TestBufferPackStreamUnpack().testBigInteger();
    }

    private static class TestStreamPackStreamUnpack extends TestSet {
	@Test @Override
	public void testBigInteger() throws Exception {
	    super.testBigInteger();
	}

	@Override
	public void testBigInteger(BigInteger v) throws Exception {
	    MessagePack msgpack = new MessagePack();
	    Template<BigInteger> tmpl = BigIntegerTemplate.instance;
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    StreamPacker packer = msgpack.createStreamPacker(out);
	    tmpl.write(packer, v);
	    byte[] bytes = out.toByteArray();
	    StreamUnpacker unpacker = msgpack.createStreamUnpacker(new ByteArrayInputStream(bytes));
	    BigInteger ret = tmpl.read(unpacker, null);
	    assertEquals(v, ret);
	}
    }

    private static class TestStreamPackBufferUnpack extends TestSet {
	@Test @Override
	public void testBigInteger() throws Exception {
	    super.testBigInteger();
	}

	@Override
	public void testBigInteger(BigInteger v) throws Exception {
	    MessagePack msgpack = new MessagePack();
	    Template<BigInteger> tmpl = BigIntegerTemplate.instance;
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    StreamPacker packer = msgpack.createStreamPacker(out);
	    tmpl.write(packer, v);
	    byte[] bytes = out.toByteArray();
	    BufferUnpacker unpacker = msgpack.createBufferUnpacker(bytes);
	    BigInteger ret = tmpl.read(unpacker, null);
	    assertEquals(v, ret);
	}
    }

    private static class TestBufferPackBufferUnpack extends TestSet {
	@Test @Override
	public void testBigInteger() throws Exception {
	    super.testBigInteger();
	}

	@Override
	public void testBigInteger(BigInteger v) throws Exception {
	    MessagePack msgpack = new MessagePack();
	    Template<BigInteger> tmpl = BigIntegerTemplate.instance;
	    BufferPacker packer = msgpack.createBufferPacker();
	    tmpl.write(packer, v);
	    byte[] bytes = packer.toByteArray();
	    BufferUnpacker unpacker = msgpack.createBufferUnpacker(bytes);
	    	    BigInteger ret = tmpl.read(unpacker, null);
	    assertEquals(v, ret);
	}
    }

    private static class TestBufferPackStreamUnpack extends TestSet {
	@Test @Override
	public void testBigInteger() throws Exception {
	    super.testBigInteger();
	}

	@Override
	public void testBigInteger(BigInteger v) throws Exception {
	    MessagePack msgpack = new MessagePack();
	    Template<BigInteger> tmpl = BigIntegerTemplate.instance;
	    BufferPacker packer = msgpack.createBufferPacker();
	    tmpl.write(packer, v);
	    byte[] bytes = packer.toByteArray();
	    StreamUnpacker unpacker = msgpack.createStreamUnpacker(new ByteArrayInputStream(bytes));
	    BigInteger ret = tmpl.read(unpacker, null);
	    assertEquals(v, ret);
	}
    }
}
