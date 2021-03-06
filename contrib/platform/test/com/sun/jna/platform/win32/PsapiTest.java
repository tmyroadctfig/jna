/* Copyright (c) 2015 Andreas "PAX" L\u00FCck, All Rights Reserved
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.  
 */
package com.sun.jna.platform.win32;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import org.junit.Test;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.Psapi.MODULEINFO;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

/**
 * Applies API tests on {@link Psapi}.
 * 
 * @author Andreas "PAX" L&uuml;ck, onkelpax-git[at]yahoo.de
 */
public class PsapiTest {
	@Test
	public void testGetModuleFileNameEx() {
		final JFrame w = new JFrame();
		try {
			w.setVisible(true);
			final String searchSubStr = "\\bin\\java";
			final HWND hwnd = new HWND(Native.getComponentPointer(w));

			final IntByReference pid = new IntByReference();
			User32.INSTANCE.GetWindowThreadProcessId(hwnd, pid);

			final HANDLE process = Kernel32.INSTANCE.OpenProcess(
					0x0400 | 0x0010, false, pid.getValue());
			if (process == null)
				throw new Win32Exception(Kernel32.INSTANCE.GetLastError());

			// check ANSI function
			final byte[] filePathAnsi = new byte[1025];
			int length = Psapi.INSTANCE.GetModuleFileNameExA(process, null,
					filePathAnsi, filePathAnsi.length - 1);
			if (length == 0)
				throw new Win32Exception(Kernel32.INSTANCE.GetLastError());

			assertTrue(
					"Path didn't contain '" + searchSubStr + "': "
							+ Native.toString(filePathAnsi),
					Native.toString(filePathAnsi).toLowerCase()
							.contains(searchSubStr));

			// check Unicode function
			final char[] filePathUnicode = new char[1025];
			length = Psapi.INSTANCE.GetModuleFileNameExW(process, null,
					filePathUnicode, filePathUnicode.length - 1);
			if (length == 0)
				throw new Win32Exception(Kernel32.INSTANCE.GetLastError());

			assertTrue(
					"Path didn't contain '" + searchSubStr + "': "
							+ Native.toString(filePathUnicode),
					Native.toString(filePathUnicode).toLowerCase()
							.contains(searchSubStr));

			// check default function
			final int memAllocSize = 1025 * Native.WCHAR_SIZE;
			final Memory filePathDefault = new Memory(memAllocSize);
			length = Psapi.INSTANCE.GetModuleFileNameEx(process, null,
					filePathDefault, (memAllocSize / Native.WCHAR_SIZE) - 1);
			if (length == 0)
				throw new Win32Exception(Kernel32.INSTANCE.GetLastError());

			assertTrue(
					"Path didn't contain '"
							+ searchSubStr
							+ "': "
							+ Native.toString(filePathDefault.getCharArray(0,
									memAllocSize / Native.WCHAR_SIZE)),
					Native.toString(
							filePathDefault.getCharArray(0, memAllocSize
									/ Native.WCHAR_SIZE)).toLowerCase()
							.contains(searchSubStr));
		} finally {
			w.dispose();
		}
	}
	
	@Test
    public void testEnumProcessModules() {
        HANDLE me = null;
        Win32Exception we = null;

        try {
            me = Kernel32.INSTANCE.OpenProcess(WinNT.PROCESS_ALL_ACCESS, false, Kernel32.INSTANCE.GetCurrentProcessId());
            assertTrue("Handle to my process should not be null", me != null);
            
            List<HMODULE> list = new LinkedList<HMODULE>();

            HMODULE[] lphModule = new HMODULE[100 * 4];
            IntByReference lpcbNeeded = new IntByReference();

            if (!Psapi.INSTANCE.EnumProcessModules(me, lphModule, lphModule.length, lpcbNeeded)) {
                throw new Win32Exception(Native.getLastError());
            }

            for (int i = 0; i < lpcbNeeded.getValue() / 4; i++) {
                list.add(lphModule[i]);
            }

            assertTrue("List should have at least 1 item in it.", list.size() > 0);
        } catch (Win32Exception e) {
            we = e;
        } finally {
            if (me != null) {
                if (!Kernel32.INSTANCE.CloseHandle(me)) {
                    Win32Exception e = new Win32Exception(Native.getLastError());
                    if (we != null) {
                        e.addSuppressed(we);
                    }
                    we = e;
                }
            }
        }
        if (we != null) {
            throw we;
        }

    }
	
	@Test
    public void testGetModuleInformation() {
        HANDLE me = null;
        Win32Exception we = null;

        try {
            me = Kernel32.INSTANCE.OpenProcess(WinNT.PROCESS_ALL_ACCESS, false, Kernel32.INSTANCE.GetCurrentProcessId());
            assertTrue("Handle to my process should not be null", me != null);
            
            List<HMODULE> list = new LinkedList<HMODULE>();

            HMODULE[] lphModule = new HMODULE[100 * 4];
            IntByReference lpcbNeeded = new IntByReference();

            if (!Psapi.INSTANCE.EnumProcessModules(me, lphModule, lphModule.length, lpcbNeeded)) {
                throw new Win32Exception(Native.getLastError());
            }

            for (int i = 0; i < lpcbNeeded.getValue() / 4; i++) {
                list.add(lphModule[i]);
            }

            assertTrue("List should have at least 1 item in it.", list.size() > 0);
            
            MODULEINFO lpmodinfo = new MODULEINFO();
            
            if (!Psapi.INSTANCE.GetModuleInformation(me, list.get(0), lpmodinfo, lpmodinfo.size())) {
                throw new Win32Exception(Native.getLastError());
            }
            
            assertTrue("MODULEINFO.EntryPoint should not be null.", lpmodinfo.EntryPoint != null);
            
        } catch (Win32Exception e) {
            we = e;
        } finally {
            if (me != null) {
                if (!Kernel32.INSTANCE.CloseHandle(me)) {
                    Win32Exception e = new Win32Exception(Native.getLastError());
                    if (we != null) {
                        e.addSuppressed(we);
                    }
                    we = e;
                }
            }
        }
        if (we != null) {
            throw we;
        }

    }
	
	@Test
    public void testGetProcessImageFileName() {
        HANDLE me = null;
        Win32Exception we = null;

        try {
            me = Kernel32.INSTANCE.OpenProcess(WinNT.PROCESS_ALL_ACCESS, false, Kernel32.INSTANCE.GetCurrentProcessId());
            assertTrue("Handle to my process should not be null", me != null);
            
            char[] buffer = new char[256];
            Psapi.INSTANCE.GetProcessImageFileName(me, buffer, 256);
            String path = new String(buffer);
            assertTrue("Image path should contain 'java' and '.exe'", path.contains("java") && path.contains(".exe"));
        } catch (Win32Exception e) {
            we = e;
        } finally {
            if (me != null) {
                if (!Kernel32.INSTANCE.CloseHandle(me)) {
                    Win32Exception e = new Win32Exception(Native.getLastError());
                    if (we != null) {
                        e.addSuppressed(we);
                    }
                    we = e;
                }
            }
        }
        if (we != null) {
            throw we;
        }

    }
}
