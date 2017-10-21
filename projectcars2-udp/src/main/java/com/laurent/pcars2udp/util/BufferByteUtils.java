package com.laurent.pcars2udp.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BufferByteUtils {

	// f32
	public Float getFloat(byte[] data, int offset, int length) {
		return ByteBuffer.wrap(data, offset, length / 8).order(ByteOrder.LITTLE_ENDIAN).getFloat();
	}

	// u8, u16, u32
	public Integer getInteger(byte[] data, int offset, int length) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(data, offset, length / 8).order(ByteOrder.LITTLE_ENDIAN);
		switch (length) {
		case 8:
			return (int) byteBuffer.get();
		case 16:
			return (int) byteBuffer.getShort();
		default:
			return byteBuffer.getInt();
		}

	}

	// char
	public String getString(byte[] data, int offset, int length) {
		int size = length;
		for (int i = offset; i < offset + length; i++) {
			if (data[i] == 0) {
				size = i - offset;
				break;
			}

		}

		return StandardCharsets.UTF_8.decode(ByteBuffer.wrap(data, offset, size).order(ByteOrder.LITTLE_ENDIAN))
				.toString().trim();
	}

	public List<String> getListString(byte[] data, int offset, int arraySize, int length) {
		List<String> liste = new ArrayList<String>();
		for (int i = 0; i < arraySize; i++) {
			liste.add(getString(data, offset + (i * length), length));
		}

		return liste;
	}

	public List<Float> getListFloat(byte[] data, int offset, int arraySize, int length) {
		List<Float> liste = new ArrayList<Float>();
		for (int i = 0; i < arraySize; i++) {
			liste.add(getFloat(data, offset + (i * length), length));
		}

		return liste;
	}
}
