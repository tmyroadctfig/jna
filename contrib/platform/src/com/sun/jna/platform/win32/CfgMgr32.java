package com.sun.jna.platform.win32;

/**
 * Cfgmgr32.dll Interface.
 *
 * @author Luke Quinane
 */
public interface CfgMgr32 {
    int CR_SUCCESS = 0x00000000;
    int CR_DEFAULT = 0x00000001;
    int CR_OUT_OF_MEMORY = 0x00000002;
    int CR_INVALID_POINTER = 0x00000003;
    int CR_INVALID_FLAG = 0x00000004;
    int CR_INVALID_DEVNODE = 0x00000005;
    int CR_INVALID_DEVINST = CR_INVALID_DEVNODE;
    int CR_INVALID_RES_DES = 0x00000006;
    int CR_INVALID_LOG_CONF = 0x00000007;
    int CR_INVALID_ARBITRATOR = 0x00000008;
    int CR_INVALID_NODELIST = 0x00000009;
    int CR_DEVNODE_HAS_REQS = 0x0000000A;
    int CR_DEVINST_HAS_REQS = CR_DEVNODE_HAS_REQS;
    int CR_INVALID_RESOURCEID = 0x0000000B;
    int CR_DLVXD_NOT_FOUND = 0x0000000C;
    int CR_NO_SUCH_DEVNODE = 0x0000000D;
    int CR_NO_SUCH_DEVINST = CR_NO_SUCH_DEVNODE;
    int CR_NO_MORE_LOG_CONF = 0x0000000E;
    int CR_NO_MORE_RES_DES = 0x0000000F;
    int CR_ALREADY_SUCH_DEVNODE = 0x00000010;
    int CR_ALREADY_SUCH_DEVINST = CR_ALREADY_SUCH_DEVNODE;
    int CR_INVALID_RANGE_LIST = 0x00000011;
    int CR_INVALID_RANGE = 0x00000012;
    int CR_FAILURE = 0x00000013;
    int CR_NO_SUCH_LOGICAL_DEV = 0x00000014;
    int CR_CREATE_BLOCKED = 0x00000015;
    int CR_NOT_SYSTEM_VM = 0x00000016;
    int CR_REMOVE_VETOED = 0x00000017;
    int CR_APM_VETOED = 0x00000018;
    int CR_INVALID_LOAD_TYPE = 0x00000019;
    int CR_BUFFER_SMALL = 0x0000001A;
    int CR_NO_ARBITRATOR = 0x0000001B;
    int CR_NO_REGISTRY_HANDLE = 0x0000001C;
    int CR_REGISTRY_ERROR = 0x0000001D;
    int CR_INVALID_DEVICE_ID = 0x0000001E;
    int CR_INVALID_DATA = 0x0000001F;
    int CR_INVALID_API = 0x00000020;
    int CR_DEVLOADER_NOT_READY = 0x00000021;
    int CR_NEED_RESTART = 0x00000022;
    int CR_NO_MORE_HW_PROFILES = 0x00000023;
    int CR_DEVICE_NOT_THERE = 0x00000024;
    int CR_NO_SUCH_VALUE = 0x00000025;
    int CR_WRONG_TYPE = 0x00000026;
    int CR_INVALID_PRIORITY = 0x00000027;
    int CR_NOT_DISABLEABLE = 0x00000028;
    int CR_FREE_RESOURCES = 0x00000029;
    int CR_QUERY_VETOED = 0x0000002A;
    int CR_CANT_SHARE_IRQ = 0x0000002B;
    int CR_NO_DEPENDENT = 0x0000002C;
    int CR_SAME_RESOURCES = 0x0000002D;
    int CR_NO_SUCH_REGISTRY_KEY = 0x0000002E;
    int CR_INVALID_MACHINENAME = 0x0000002F;
    int CR_REMOTE_COMM_FAILURE = 0x00000030;
    int CR_MACHINE_UNAVAILABLE = 0x00000031;
    int CR_NO_CM_SERVICES = 0x00000032;
    int CR_ACCESS_DENIED = 0x00000033;
    int CR_CALL_NOT_IMPLEMENTED = 0x00000034;
    int CR_INVALID_PROPERTY = 0x00000035;
    int CR_DEVICE_INTERFACE_ACTIVE = 0x00000036;
    int CR_NO_SUCH_DEVICE_INTERFACE = 0x00000037;
    int CR_INVALID_REFERENCE_STRING = 0x00000038;
    int CR_INVALID_CONFLICT_LIST = 0x00000039;
    int CR_INVALID_INDEX = 0x0000003A;
    int CR_INVALID_STRUCTURE_SIZE = 0x0000003B;
    int NUM_CR_RESULTS = 0x0000003C;
}