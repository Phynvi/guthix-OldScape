/*
 * Copyright (C) 2019 Guthix
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package io.guthix.osrs.cache.script

class Instruction(val opcode: Int, val name: String) {
    internal companion object {
        val byName = mutableMapOf<String, Instruction>()
        val byOpcode = mutableMapOf<Int, Instruction>()

        val ICONST = Instruction(0, "iconst")
        val GET_VARP = Instruction(1, "get_varp")
        val SET_VARP = Instruction(2, "set_varp")
        val SCONST = Instruction(3, "sconst")
        val JUMP = Instruction(6, "jump")
        val IF_ICMPNE = Instruction(7, "if_icmpne")
        val IF_ICMPEQ = Instruction(8, "if_icmpeq")
        val IF_ICMPLT = Instruction(9, "if_icmplt")
        val IF_ICMPGT = Instruction(10, "if_icmpgt")
        val RETURN = Instruction(21, "return")
        val GET_VARBIT = Instruction(25, "get_varbit")
        val SET_VARBIT = Instruction(27, "set_varbit")
        val IF_ICMPLE = Instruction(31, "if_icmple")
        val IF_ICMPGE = Instruction(32, "if_icmpge")
        val ILOAD = Instruction(33, "iload")
        val ISTORE = Instruction(34, "istore")
        val SLOAD = Instruction(35, "sload")
        val SSTORE = Instruction(36, "sstore")
        val JOIN_STRING = Instruction(37, "join_string")
        val POP_INT = Instruction(38, "pop_int")
        val POP_STRING = Instruction(39, "pop_string")
        val INVOKE = Instruction(40, "invoke")
        val GET_VARC_INT = Instruction(42, "get_varc_int")
        val SET_VARC_INT = Instruction(43, "set_varc_int")
        val DEFINE_ARRAY = Instruction(44, "define_array")
        val GET_ARRAY_INT = Instruction(45, "get_array_int")
        val SET_ARRAY_INT = Instruction(46, "set_array_int")
        val GET_VARC_STRING_OLD = Instruction(47, "get_varc_string_old")
        val SET_VARC_STRING_OLD = Instruction(48, "set_varc_string_old")
        val GET_VARC_STRING = Instruction(49, "get_varc_string")
        val SET_VARC_STRING = Instruction(50, "set_varc_string")
        val SWITCH = Instruction(60, "switch")
        val CC_CREATE = Instruction(100, "cc_create")
        val CC_DELETE = Instruction(101, "cc_delete")
        val CC_DELETEALL = Instruction(102, "cc_deleteall")
        val CC_FIND = Instruction(200, "cc_find")
        val IF_FIND = Instruction(201, "if_find")
        val CC_SETPOSITION = Instruction(1000, "cc_setposition")
        val CC_SETSIZE = Instruction(1001, "cc_setsize")
        val CC_SETHIDE = Instruction(1003, "cc_sethide")
        val CC_SETNOCLICKTHROUGH = Instruction(1005, "cc_setnoclickthrough")
        val CC_SETSCROLLPOS = Instruction(1100, "cc_setscrollpos")
        val CC_SETCOLOUR = Instruction(1101, "cc_setcolour")
        val CC_SETFILL = Instruction(1102, "cc_setfill")
        val CC_SETTRANS = Instruction(1103, "cc_settrans")
        val CC_SETLINEWID = Instruction(1104, "cc_setlinewid")
        val CC_SETGRAPHIC = Instruction(1105, "cc_setgraphic")
        val CC_SET2DANGLE = Instruction(1106, "cc_set2dangle")
        val CC_SETTILING = Instruction(1107, "cc_settiling")
        val CC_SETMODEL = Instruction(1108, "cc_setmodel")
        val CC_SETMODELANGLE = Instruction(1109, "cc_setmodelangle")
        val CC_SETMODELANIM = Instruction(1110, "cc_setmodelanim")
        val CC_SETMODELORTHOG = Instruction(1111, "cc_setmodelorthog")
        val CC_SETTEXT = Instruction(1112, "cc_settext")
        val CC_SETTEXTFONT = Instruction(1113, "cc_settextfont")
        val CC_SETTEXTALIGN = Instruction(1114, "cc_settextalign")
        val CC_SETTEXTSHADOW = Instruction(1115, "cc_settextshadow")
        val CC_SETOUTLINE = Instruction(1116, "cc_setoutline")
        val CC_SETGRAPHICSHADOW = Instruction(1117, "cc_setgraphicshadow")
        val CC_SETVFLIP = Instruction(1118, "cc_setvflip")
        val CC_SETHFLIP = Instruction(1119, "cc_sethflip")
        val CC_SETSCROLLSIZE = Instruction(1120, "cc_setscrollsize")
        val CC_RESUME_PAUSEBUTTON = Instruction(1121, "cc_resume_pausebutton")
        val CC_SETFILLCOLOUR = Instruction(1123, "cc_setfillcolour")
        val CC_SETLINEDIRECTION = Instruction(1126, "cc_setlinedirection")
        val CC_SETOBJECT = Instruction(1200, "cc_setobject")
        val CC_SETNPCHEAD = Instruction(1201, "cc_setnpchead")
        val CC_SETPLAYERHEAD_SELF = Instruction(1202, "cc_setplayerhead_self")
        val CC_SETOBJECT_NONUM = Instruction(1205, "cc_setobject_nonum")
        val CC_SETOBJECT_ALWAYS_NUM = Instruction(1212, "cc_setobject_always_num")
        val CC_SETOP = Instruction(1300, "cc_setop")
        val CC_SETDRAGGABLE = Instruction(1301, "cc_setdraggable")
        val CC_SETDRAGGABLEBEHAVIOR = Instruction(1302, "cc_setdraggablebehavior")
        val CC_SETDRAGDEADZONE = Instruction(1303, "cc_setdragdeadzone")
        val CC_SETDRAGDEADTIME = Instruction(1304, "cc_setdragdeadtime")
        val CC_SETOPBASE = Instruction(1305, "cc_setopbase")
        val CC_SETTARGETVERB = Instruction(1306, "cc_settargetverb")
        val CC_CLEAROPS = Instruction(1307, "cc_clearops")
        val CC_SETONCLICK = Instruction(1400, "cc_setonclick")
        val CC_SETONHOLD = Instruction(1401, "cc_setonhold")
        val CC_SETONRELEASE = Instruction(1402, "cc_setonrelease")
        val CC_SETONMOUSEOVER = Instruction(1403, "cc_setonmouseover")
        val CC_SETONMOUSELEAVE = Instruction(1404, "cc_setonmouseleave")
        val CC_SETONDRAG = Instruction(1405, "cc_setondrag")
        val CC_SETONTARGETLEAVE = Instruction(1406, "cc_setontargetleave")
        val CC_SETONVARTRANSMIT = Instruction(1407, "cc_setonvartransmit")
        val CC_SETONTIMER = Instruction(1408, "cc_setontimer")
        val CC_SETONOP = Instruction(1409, "cc_setonop")
        val CC_SETONDRAGCOMPLETE = Instruction(1410, "cc_setondragcomplete")
        val CC_SETONCLICKREPEAT = Instruction(1411, "cc_setonclickrepeat")
        val CC_SETONMOUSEREPEAT = Instruction(1412, "cc_setonmouserepeat")
        val CC_SETONINVTRANSMIT = Instruction(1414, "cc_setoninvtransmit")
        val CC_SETONSTATTRANSMIT = Instruction(1415, "cc_setonstattransmit")
        val CC_SETONTARGETENTER = Instruction(1416, "cc_setontargetenter")
        val CC_SETONSCROLLWHEEL = Instruction(1417, "cc_setonscrollwheel")
        val CC_SETONCHATTRANSMIT = Instruction(1418, "cc_setonchattransmit")
        val CC_SETONKEY = Instruction(1419, "cc_setonkey")
        val CC_SETONFRIENDTRANSMIT = Instruction(1420, "cc_setonfriendtransmit")
        val CC_SETONCLANTRANSMIT = Instruction(1421, "cc_setonclantransmit")
        val CC_SETONMISCTRANSMIT = Instruction(1422, "cc_setonmisctransmit")
        val CC_SETONDIALOGABORT = Instruction(1423, "cc_setondialogabort")
        val CC_SETONSUBCHANGE = Instruction(1424, "cc_setonsubchange")
        val CC_SETONSTOCKTRANSMIT = Instruction(1425, "cc_setonstocktransmit")
        val CC_SETONRESIZE = Instruction(1427, "cc_setonresize")
        val CC_GETX = Instruction(1500, "cc_getx")
        val CC_GETY = Instruction(1501, "cc_gety")
        val CC_GETWIDTH = Instruction(1502, "cc_getwidth")
        val CC_GETHEIGHT = Instruction(1503, "cc_getheight")
        val CC_GETHIDE = Instruction(1504, "cc_gethide")
        val CC_GETLAYER = Instruction(1505, "cc_getlayer")
        val CC_GETSCROLLX = Instruction(1600, "cc_getscrollx")
        val CC_GETSCROLLY = Instruction(1601, "cc_getscrolly")
        val CC_GETTEXT = Instruction(1602, "cc_gettext")
        val CC_GETSCROLLWIDTH = Instruction(1603, "cc_getscrollwidth")
        val CC_GETSCROLLHEIGHT = Instruction(1604, "cc_getscrollheight")
        val CC_GETMODELZOOM = Instruction(1605, "cc_getmodelzoom")
        val CC_GETMODELANGLE_X = Instruction(1606, "cc_getmodelangle_x")
        val CC_GETMODELANGLE_Z = Instruction(1607, "cc_getmodelangle_z")
        val CC_GETMODELANGLE_Y = Instruction(1608, "cc_getmodelangle_y")
        val CC_GETTRANS = Instruction(1609, "cc_gettrans")
        val CC_GETCOLOUR = Instruction(1611, "cc_getcolour")
        val CC_GETFILLCOLOUR = Instruction(1612, "cc_getfillcolour")
        val CC_GETINVOBJECT = Instruction(1700, "cc_getinvobject")
        val CC_GETINVCOUNT = Instruction(1701, "cc_getinvcount")
        val CC_GETID = Instruction(1702, "cc_getid")
        val CC_GETTARGETMASK = Instruction(1800, "cc_gettargetmask")
        val CC_GETOP = Instruction(1801, "cc_getop")
        val CC_GETOPBASE = Instruction(1802, "cc_getopbase")
        val CC_CALLONRESIZE = Instruction(1927, "cc_callonresize")
        val IF_SETPOSITION = Instruction(2000, "if_setposition")
        val IF_SETSIZE = Instruction(2001, "if_setsize")
        val IF_SETHIDE = Instruction(2003, "if_sethide")
        val IF_SETNOCLICKTHROUGH = Instruction(2005, "if_setnoclickthrough")
        val IF_SETSCROLLPOS = Instruction(2100, "if_setscrollpos")
        val IF_SETCOLOUR = Instruction(2101, "if_setcolour")
        val IF_SETFILL = Instruction(2102, "if_setfill")
        val IF_SETTRANS = Instruction(2103, "if_settrans")
        val IF_SETLINEWID = Instruction(2104, "if_setlinewid")
        val IF_SETGRAPHIC = Instruction(2105, "if_setgraphic")
        val IF_SET2DANGLE = Instruction(2106, "if_set2dangle")
        val IF_SETTILING = Instruction(2107, "if_settiling")
        val IF_SETMODEL = Instruction(2108, "if_setmodel")
        val IF_SETMODELANGLE = Instruction(2109, "if_setmodelangle")
        val IF_SETMODELANIM = Instruction(2110, "if_setmodelanim")
        val IF_SETMODELORTHOG = Instruction(2111, "if_setmodelorthog")
        val IF_SETTEXT = Instruction(2112, "if_settext")
        val IF_SETTEXTFONT = Instruction(2113, "if_settextfont")
        val IF_SETTEXTALIGN = Instruction(2114, "if_settextalign")
        val IF_SETTEXTSHADOW = Instruction(2115, "if_settextshadow")
        val IF_SETOUTLINE = Instruction(2116, "if_setoutline")
        val IF_SETGRAPHICSHADOW = Instruction(2117, "if_setgraphicshadow")
        val IF_SETVFLIP = Instruction(2118, "if_setvflip")
        val IF_SETHFLIP = Instruction(2119, "if_sethflip")
        val IF_SETSCROLLSIZE = Instruction(2120, "if_setscrollsize")
        val IF_RESUME_PAUSEBUTTON = Instruction(2121, "if_resume_pausebutton")
        val IF_SETFILLCOLOUR = Instruction(2123, "if_setfillcolour")
        val IF_SETLINEDIRECTION = Instruction(2126, "if_setlinedirection")
        val IF_SETOBJECT = Instruction(2200, "if_setobject")
        val IF_SETNPCHEAD = Instruction(2201, "if_setnpchead")
        val IF_SETPLAYERHEAD_SELF = Instruction(2202, "if_setplayerhead_self")
        val IF_SETOBJECT_NONUM = Instruction(2205, "if_setobject_nonum")
        val IF_SETOBJECT_ALWAYS_NUM = Instruction(2212, "if_setobject_always_num")
        val IF_SETOP = Instruction(2300, "if_setop")
        val IF_SETDRAGGABLE = Instruction(2301, "if_setdraggable")
        val IF_SETDRAGGABLEBEHAVIOR = Instruction(2302, "if_setdraggablebehavior")
        val IF_SETDRAGDEADZONE = Instruction(2303, "if_setdragdeadzone")
        val IF_SETDRAGDEADTIME = Instruction(2304, "if_setdragdeadtime")
        val IF_SETOPBASE = Instruction(2305, "if_setopbase")
        val IF_SETTARGETVERB = Instruction(2306, "if_settargetverb")
        val IF_CLEAROPS = Instruction(2307, "if_clearops")
        val IF_SETOPKEY = Instruction(2350, "if_setopkey")
        val IF_SETOPTKEY = Instruction(2351, "if_setoptkey")
        val IF_SETOPKEYRATE = Instruction(2352, "if_setopkeyrate")
        val IF_SETOPTKEYRATE = Instruction(2353, "if_setoptkeyrate")
        val IF_SETOPKEYIGNOREHELD = Instruction(2354, "if_setopkeyignoreheld")
        val IF_SETOPTKEYIGNOREHELD = Instruction(2355, "if_setoptkeyignoreheld")
        val IF_SETONCLICK = Instruction(2400, "if_setonclick")
        val IF_SETONHOLD = Instruction(2401, "if_setonhold")
        val IF_SETONRELEASE = Instruction(2402, "if_setonrelease")
        val IF_SETONMOUSEOVER = Instruction(2403, "if_setonmouseover")
        val IF_SETONMOUSELEAVE = Instruction(2404, "if_setonmouseleave")
        val IF_SETONDRAG = Instruction(2405, "if_setondrag")
        val IF_SETONTARGETLEAVE = Instruction(2406, "if_setontargetleave")
        val IF_SETONVARTRANSMIT = Instruction(2407, "if_setonvartransmit")
        val IF_SETONTIMER = Instruction(2408, "if_setontimer")
        val IF_SETONOP = Instruction(2409, "if_setonop")
        val IF_SETONDRAGCOMPLETE = Instruction(2410, "if_setondragcomplete")
        val IF_SETONCLICKREPEAT = Instruction(2411, "if_setonclickrepeat")
        val IF_SETONMOUSEREPEAT = Instruction(2412, "if_setonmouserepeat")
        val IF_SETONINVTRANSMIT = Instruction(2414, "if_setoninvtransmit")
        val IF_SETONSTATTRANSMIT = Instruction(2415, "if_setonstattransmit")
        val IF_SETONTARGETENTER = Instruction(2416, "if_setontargetenter")
        val IF_SETONSCROLLWHEEL = Instruction(2417, "if_setonscrollwheel")
        val IF_SETONCHATTRANSMIT = Instruction(2418, "if_setonchattransmit")
        val IF_SETONKEY = Instruction(2419, "if_setonkey")
        val IF_SETONFRIENDTRANSMIT = Instruction(2420, "if_setonfriendtransmit")
        val IF_SETONCLANTRANSMIT = Instruction(2421, "if_setonclantransmit")
        val IF_SETONMISCTRANSMIT = Instruction(2422, "if_setonmisctransmit")
        val IF_SETONDIALOGABORT = Instruction(2423, "if_setondialogabort")
        val IF_SETONSUBCHANGE = Instruction(2424, "if_setonsubchange")
        val IF_SETONSTOCKTRANSMIT = Instruction(2425, "if_setonstocktransmit")
        val IF_SETONRESIZE = Instruction(2427, "if_setonresize")
        val IF_GETX = Instruction(2500, "if_getx")
        val IF_GETY = Instruction(2501, "if_gety")
        val IF_GETWIDTH = Instruction(2502, "if_getwidth")
        val IF_GETHEIGHT = Instruction(2503, "if_getheight")
        val IF_GETHIDE = Instruction(2504, "if_gethide")
        val IF_GETLAYER = Instruction(2505, "if_getlayer")
        val IF_GETSCROLLX = Instruction(2600, "if_getscrollx")
        val IF_GETSCROLLY = Instruction(2601, "if_getscrolly")
        val IF_GETTEXT = Instruction(2602, "if_gettext")
        val IF_GETSCROLLWIDTH = Instruction(2603, "if_getscrollwidth")
        val IF_GETSCROLLHEIGHT = Instruction(2604, "if_getscrollheight")
        val IF_GETMODELZOOM = Instruction(2605, "if_getmodelzoom")
        val IF_GETMODELANGLE_X = Instruction(2606, "if_getmodelangle_x")
        val IF_GETMODELANGLE_Z = Instruction(2607, "if_getmodelangle_z")
        val IF_GETMODELANGLE_Y = Instruction(2608, "if_getmodelangle_y")
        val IF_GETTRANS = Instruction(2609, "if_gettrans")
        val IF_GETCOLOUR = Instruction(2611, "if_getcolour")
        val IF_GETFILLCOLOUR = Instruction(2612, "if_getfillcolour")
        val IF_GETINVOBJECT = Instruction(2700, "if_getinvobject")
        val IF_GETINVCOUNT = Instruction(2701, "if_getinvcount")
        val IF_HASSUB = Instruction(2702, "if_hassub")
        val IF_GETTOP = Instruction(2706, "if_gettop")
        val IF_GETTARGETMASK = Instruction(2800, "if_gettargetmask")
        val IF_GETOP = Instruction(2801, "if_getop")
        val IF_GETOPBASE = Instruction(2802, "if_getopbase")
        val IF_CALLONRESIZE = Instruction(2927, "if_callonresize")
        val MES = Instruction(3100, "mes")
        val ANIM = Instruction(3101, "anim")
        val IF_CLOSE = Instruction(3103, "if_close")
        val RESUME_COUNTDIALOG = Instruction(3104, "resume_countdialog")
        val RESUME_NAMEDIALOG = Instruction(3105, "resume_namedialog")
        val RESUME_STRINGDIALOG = Instruction(3106, "resume_stringdialog")
        val OPPLAYER = Instruction(3107, "opplayer")
        val IF_DRAGPICKUP = Instruction(3108, "if_dragpickup")
        val CC_DRAGPICKUP = Instruction(3109, "cc_dragpickup")
        val MOUSECAM = Instruction(3110, "mousecam")
        val GETREMOVEROOFS = Instruction(3111, "getremoveroofs")
        val SETREMOVEROOFS = Instruction(3112, "setremoveroofs")
        val OPENURL = Instruction(3113, "openurl")
        val RESUME_OBJDIALOG = Instruction(3115, "resume_objdialog")
        val BUG_REPORT = Instruction(3116, "bug_report")
        val SETSHIFTCLICKDROP = Instruction(3117, "setshiftclickdrop")
        val SETSHOWMOUSEOVERTEXT = Instruction(3118, "setshowmouseovertext")
        val RENDERSELF = Instruction(3119, "renderself")
        val SETSHOWMOUSECROSS = Instruction(3125, "setshowmousecross")
        val SETSHOWLOADINGMESSAGES = Instruction(3126, "setshowloadingmessages")
        val SETTAPTODROP = Instruction(3127, "settaptodrop")
        val GETTAPTODROP = Instruction(3128, "gettaptodrop")
        val GETCANVASSIZE = Instruction(3132, "getcanvassize")
        val SETHIDEUSERNAME = Instruction(3141, "sethideusername")
        val GETHIDEUSERNAME = Instruction(3142, "gethideusername")
        val SETREMEMBERUSERNAME = Instruction(3143, "setrememberusername")
        val GETREMEMBERUSERNAME = Instruction(3144, "getrememberusername")
        val SOUND_SYNTH = Instruction(3200, "sound_synth")
        val SOUND_SONG = Instruction(3201, "sound_song")
        val SOUND_JINGLE = Instruction(3202, "sound_jingle")
        val CLIENTCLOCK = Instruction(3300, "clientclock")
        val INV_GETOBJ = Instruction(3301, "inv_getobj")
        val INV_GETNUM = Instruction(3302, "inv_getnum")
        val INV_TOTAL = Instruction(3303, "inv_total")
        val INV_SIZE = Instruction(3304, "inv_size")
        val STAT = Instruction(3305, "stat")
        val STAT_BASE = Instruction(3306, "stat_base")
        val STAT_XP = Instruction(3307, "stat_xp")
        val COORD = Instruction(3308, "coord")
        val COORDX = Instruction(3309, "coordx")
        val COORDZ = Instruction(3310, "coordz")
        val COORDY = Instruction(3311, "coordy")
        val MAP_MEMBERS = Instruction(3312, "map_members")
        val INVOTHER_GETOBJ = Instruction(3313, "invother_getobj")
        val INVOTHER_GETNUM = Instruction(3314, "invother_getnum")
        val INVOTHER_TOTAL = Instruction(3315, "invother_total")
        val STAFFMODLEVEL = Instruction(3316, "staffmodlevel")
        val REBOOTTIMER = Instruction(3317, "reboottimer")
        val MAP_WORLD = Instruction(3318, "map_world")
        val RUNENERGY_VISIBLE = Instruction(3321, "runenergy_visible")
        val RUNWEIGHT_VISIBLE = Instruction(3322, "runweight_visible")
        val PLAYERMOD = Instruction(3323, "playermod")
        val WORLDFLAGS = Instruction(3324, "worldflags")
        val MOVECOORD = Instruction(3325, "movecoord")
        val ENUM_STRING = Instruction(3400, "enum_string")
        val ENUM = Instruction(3408, "enum")
        val ENUM_GETOUTPUTCOUNT = Instruction(3411, "enum_getoutputcount")
        val FRIEND_COUNT = Instruction(3600, "friend_count")
        val FRIEND_GETNAME = Instruction(3601, "friend_getname")
        val FRIEND_GETWORLD = Instruction(3602, "friend_getworld")
        val FRIEND_GETRANK = Instruction(3603, "friend_getrank")
        val FRIEND_SETRANK = Instruction(3604, "friend_setrank")
        val FRIEND_ADD = Instruction(3605, "friend_add")
        val FRIEND_DEL = Instruction(3606, "friend_del")
        val IGNORE_ADD = Instruction(3607, "ignore_add")
        val IGNORE_DEL = Instruction(3608, "ignore_del")
        val FRIEND_TEST = Instruction(3609, "friend_test")
        val CLAN_GETCHATDISPLAYNAME = Instruction(3611, "clan_getchatdisplayname")
        val CLAN_GETCHATCOUNT = Instruction(3612, "clan_getchatcount")
        val CLAN_GETCHATUSERNAME = Instruction(3613, "clan_getchatusername")
        val CLAN_GETCHATUSERWORLD = Instruction(3614, "clan_getchatuserworld")
        val CLAN_GETCHATUSERRANK = Instruction(3615, "clan_getchatuserrank")
        val CLAN_GETCHATMINKICK = Instruction(3616, "clan_getchatminkick")
        val CLAN_KICKUSER = Instruction(3617, "clan_kickuser")
        val CLAN_GETCHATRANK = Instruction(3618, "clan_getchatrank")
        val CLAN_JOINCHAT = Instruction(3619, "clan_joinchat")
        val CLAN_LEAVECHAT = Instruction(3620, "clan_leavechat")
        val IGNORE_COUNT = Instruction(3621, "ignore_count")
        val IGNORE_GETNAME = Instruction(3622, "ignore_getname")
        val IGNORE_TEST = Instruction(3623, "ignore_test")
        val CLAN_ISSELF = Instruction(3624, "clan_isself")
        val CLAN_GETCHATOWNERNAME = Instruction(3625, "clan_getchatownername")
        val CLAN_ISFRIEND = Instruction(3626, "clan_isfriend")
        val CLAN_ISIGNORE = Instruction(3627, "clan_isignore")
        val STOCKMARKET_GETOFFERTYPE = Instruction(3903, "stockmarket_getoffertype")
        val STOCKMARKET_GETOFFERITEM = Instruction(3904, "stockmarket_getofferitem")
        val STOCKMARKET_GETOFFERPRICE = Instruction(3905, "stockmarket_getofferprice")
        val STOCKMARKET_GETOFFERCOUNT = Instruction(3906, "stockmarket_getoffercount")
        val STOCKMARKET_GETOFFERCOMPLETEDCOUNT = Instruction(3907, "stockmarket_getoffercompletedcount")
        val STOCKMARKET_GETOFFERCOMPLETEDGOLD = Instruction(3908, "stockmarket_getoffercompletedgold")
        val STOCKMARKET_ISOFFEREMPTY = Instruction(3910, "stockmarket_isofferempty")
        val STOCKMARKET_ISOFFERSTABLE = Instruction(3911, "stockmarket_isofferstable")
        val STOCKMARKET_ISOFFERFINISHED = Instruction(3912, "stockmarket_isofferfinished")
        val STOCKMARKET_ISOFFERADDING = Instruction(3913, "stockmarket_isofferadding")
        val TRADINGPOST_SORTBY_NAME = Instruction(3914, "tradingpost_sortby_name")
        val TRADINGPOST_SORTBY_PRICE = Instruction(3915, "tradingpost_sortby_price")
        val TRADINGPOST_SORTFILTERBY_WORLD = Instruction(3916, "tradingpost_sortfilterby_world")
        val TRADINGPOST_SORTBY_AGE = Instruction(3917, "tradingpost_sortby_age")
        val TRADINGPOST_SORTBY_COUNT = Instruction(3918, "tradingpost_sortby_count")
        val TRADINGPOST_GETTOTALOFFERS = Instruction(3919, "tradingpost_gettotaloffers")
        val TRADINGPOST_GETOFFERWORLD = Instruction(3920, "tradingpost_getofferworld")
        val TRADINGPOST_GETOFFERNAME = Instruction(3921, "tradingpost_getoffername")
        val TRADINGPOST_GETOFFERPREVIOUSNAME = Instruction(3922, "tradingpost_getofferpreviousname")
        val TRADINGPOST_GETOFFERAGE = Instruction(3923, "tradingpost_getofferage")
        val TRADINGPOST_GETOFFERCOUNT = Instruction(3924, "tradingpost_getoffercount")
        val TRADINGPOST_GETOFFERPRICE = Instruction(3925, "tradingpost_getofferprice")
        val TRADINGPOST_GETOFFERITEM = Instruction(3926, "tradingpost_getofferitem")
        val ADD = Instruction(4000, "add")
        val SUB = Instruction(4001, "sub")
        val MULTIPLY = Instruction(4002, "multiply")
        val DIV = Instruction(4003, "div")
        val RANDOM = Instruction(4004, "random")
        val RANDOMINC = Instruction(4005, "randominc")
        val INTERPOLATE = Instruction(4006, "interpolate")
        val ADDPERCENT = Instruction(4007, "addpercent")
        val SETBIT = Instruction(4008, "setbit")
        val CLEARBIT = Instruction(4009, "clearbit")
        val TESTBIT = Instruction(4010, "testbit")
        val MOD = Instruction(4011, "mod")
        val POW = Instruction(4012, "pow")
        val INVPOW = Instruction(4013, "invpow")
        val AND = Instruction(4014, "and")
        val OR = Instruction(4015, "or")
        val SCALE = Instruction(4018, "scale")
        val APPEND_NUM = Instruction(4100, "append_num")
        val APPEND = Instruction(4101, "append")
        val APPEND_SIGNNUM = Instruction(4102, "append_signnum")
        val LOWERCASE = Instruction(4103, "lowercase")
        val FROMDATE = Instruction(4104, "fromdate")
        val TEXT_GENDER = Instruction(4105, "text_gender")
        val TOSTRING = Instruction(4106, "tostring")
        val COMPARE = Instruction(4107, "compare")
        val PARAHEIGHT = Instruction(4108, "paraheight")
        val PARAWIDTH = Instruction(4109, "parawidth")
        val TEXT_SWITCH = Instruction(4110, "text_switch")
        val ESCAPE = Instruction(4111, "escape")
        val APPEND_CHAR = Instruction(4112, "append_char")
        val CHAR_ISPRINTABLE = Instruction(4113, "char_isprintable")
        val CHAR_ISALPHANUMERIC = Instruction(4114, "char_isalphanumeric")
        val CHAR_ISALPHA = Instruction(4115, "char_isalpha")
        val CHAR_ISNUMERIC = Instruction(4116, "char_isnumeric")
        val STRING_LENGTH = Instruction(4117, "string_length")
        val SUBSTRING = Instruction(4118, "substring")
        val REMOVETAGS = Instruction(4119, "removetags")
        val STRING_INDEXOF_CHAR = Instruction(4120, "string_indexof_char")
        val STRING_INDEXOF_STRING = Instruction(4121, "string_indexof_string")
        val OC_NAME = Instruction(4200, "oc_name")
        val OC_OP = Instruction(4201, "oc_op")
        val OC_IOP = Instruction(4202, "oc_iop")
        val OC_COST = Instruction(4203, "oc_cost")
        val OC_STACKABLE = Instruction(4204, "oc_stackable")
        val OC_CERT = Instruction(4205, "oc_cert")
        val OC_UNCERT = Instruction(4206, "oc_uncert")
        val OC_MEMBERS = Instruction(4207, "oc_members")
        val OC_PLACEHOLDER = Instruction(4208, "oc_placeholder")
        val OC_UNPLACEHOLDER = Instruction(4209, "oc_unplaceholder")
        val OC_FIND = Instruction(4210, "oc_find")
        val OC_FINDNEXT = Instruction(4211, "oc_findnext")
        val OC_FINDRESET = Instruction(4212, "oc_findreset")
        val CHAT_GETFILTER_PUBLIC = Instruction(5000, "chat_getfilter_public")
        val CHAT_SETFILTER = Instruction(5001, "chat_setfilter")
        val CHAT_SENDABUSEREPORT = Instruction(5002, "chat_sendabusereport")
        val CHAT_GETHISTORY_BYTYPEANDLINE = Instruction(5003, "chat_gethistory_bytypeandline")
        val CHAT_GETHISTORY_BYUID = Instruction(5004, "chat_gethistory_byuid")
        val CHAT_GETFILTER_PRIVATE = Instruction(5005, "chat_getfilter_private")
        val CHAT_SENDPUBLIC = Instruction(5008, "chat_sendpublic")
        val CHAT_SENDPRIVATE = Instruction(5009, "chat_sendprivate")
        val CHAT_PLAYERNAME = Instruction(5015, "chat_playername")
        val CHAT_GETFILTER_TRADE = Instruction(5016, "chat_getfilter_trade")
        val CHAT_GETHISTORYLENGTH = Instruction(5017, "chat_gethistorylength")
        val CHAT_GETNEXTUID = Instruction(5018, "chat_getnextuid")
        val CHAT_GETPREVUID = Instruction(5019, "chat_getprevuid")
        val DOCHEAT = Instruction(5020, "docheat")
        val CHAT_SETMESSAGEFILTER = Instruction(5021, "chat_setmessagefilter")
        val CHAT_GETMESSAGEFILTER = Instruction(5022, "chat_getmessagefilter")
        val GETWINDOWMODE = Instruction(5306, "getwindowmode")
        val SETWINDOWMODE = Instruction(5307, "setwindowmode")
        val GETDEFAULTWINDOWMODE = Instruction(5308, "getdefaultwindowmode")
        val SETDEFAULTWINDOWMODE = Instruction(5309, "setdefaultwindowmode")
        val CAM_FORCEANGLE = Instruction(5504, "cam_forceangle")
        val CAM_GETANGLE_XA = Instruction(5505, "cam_getangle_xa")
        val CAM_GETANGLE_YA = Instruction(5506, "cam_getangle_ya")
        val CAM_SETFOLLOWHEIGHT = Instruction(5530, "cam_setfollowheight")
        val CAM_GETFOLLOWHEIGHT = Instruction(5531, "cam_getfollowheight")
        val LOGOUT = Instruction(5630, "logout")
        val VIEWPORT_SETFOV = Instruction(6200, "viewport_setfov")
        val VIEWPORT_SETZOOM = Instruction(6201, "viewport_setzoom")
        val VIEWPORT_CLAMPFOV = Instruction(6202, "viewport_clampfov")
        val VIEWPORT_GETEFFECTIVESIZE = Instruction(6203, "viewport_geteffectivesize")
        val VIEWPORT_GETZOOM = Instruction(6204, "viewport_getzoom")
        val VIEWPORT_GETFOV = Instruction(6205, "viewport_getfov")
        val WORLDLIST_FETCH = Instruction(6500, "worldlist_fetch")
        val WORLDLIST_START = Instruction(6501, "worldlist_start")
        val WORLDLIST_NEXT = Instruction(6502, "worldlist_next")
        val WORLDLIST_SPECIFIC = Instruction(6506, "worldlist_specific")
        val WORLDLIST_SORT = Instruction(6507, "worldlist_sort")
        val SETFOLLOWEROPSLOWPRIORITY = Instruction(6512, "setfolloweropslowpriority")
        val NC_PARAM = Instruction(6513, "nc_param")
        val LC_PARAM = Instruction(6514, "lc_param")
        val OC_PARAM = Instruction(6515, "oc_param")
        val STRUCT_PARAM = Instruction(6516, "struct_param")
        val ON_MOBILE = Instruction(6518, "on_mobile")
        val CLIENTTYPE = Instruction(6519, "clienttype")
        val BATTERYLEVEL = Instruction(6524, "batterylevel")
        val BATTERYCHARGING = Instruction(6525, "batterycharging")
        val WIFIAVAILABLE = Instruction(6526, "wifiavailable")
        val WORLDMAP_GETMAPNAME = Instruction(6601, "worldmap_getmapname")
        val WORLDMAP_SETMAP = Instruction(6602, "worldmap_setmap")
        val WORLDMAP_GETZOOM = Instruction(6603, "worldmap_getzoom")
        val WORLDMAP_SETZOOM = Instruction(6604, "worldmap_setzoom")
        val WORLDMAP_ISLOADED = Instruction(6605, "worldmap_isloaded")
        val WORLDMAP_JUMPTODISPLAYCOORD = Instruction(6606, "worldmap_jumptodisplaycoord")
        val WORLDMAP_JUMPTODISPLAYCOORD_INSTANT = Instruction(6607, "worldmap_jumptodisplaycoord_instant")
        val WORLDMAP_JUMPTOSOURCECOORD = Instruction(6608, "worldmap_jumptosourcecoord")
        val WORLDMAP_JUMPTOSOURCECOORD_INSTANT = Instruction(6609, "worldmap_jumptosourcecoord_instant")
        val WORLDMAP_GETDISPLAYPOSITION = Instruction(6610, "worldmap_getdisplayposition")
        val WORLDMAP_GETCONFIGORIGIN = Instruction(6611, "worldmap_getconfigorigin")
        val WORLDMAP_GETCONFIGSIZE = Instruction(6612, "worldmap_getconfigsize")
        val WORLDMAP_GETCONFIGBOUNDS = Instruction(6613, "worldmap_getconfigbounds")
        val WORLDMAP_GETCONFIGZOOM = Instruction(6614, "worldmap_getconfigzoom")
        val WORLDMAP_GETCURRENTMAP = Instruction(6616, "worldmap_getcurrentmap")
        val WORLDMAP_GETDISPLAYCOORD = Instruction(6617, "worldmap_getdisplaycoord")
        val WORLDMAP_COORDINMAP = Instruction(6621, "worldmap_coordinmap")
        val WORLDMAP_GETSIZE = Instruction(6622, "worldmap_getsize")
        val WORLDMAP_PERPETUALFLASH = Instruction(6628, "worldmap_perpetualflash")
        val WORLDMAP_FLASHELEMENT = Instruction(6629, "worldmap_flashelement")
        val WORLDMAP_FLASHELEMENTCATEGORY = Instruction(6630, "worldmap_flashelementcategory")
        val WORLDMAP_STOPCURRENTFLASHES = Instruction(6631, "worldmap_stopcurrentflashes")
        val WORLDMAP_DISABLEELEMENTS = Instruction(6632, "worldmap_disableelements")
        val WORLDMAP_DISABLEELEMENT = Instruction(6633, "worldmap_disableelement")
        val WORLDMAP_DISABLEELEMENTCATEGORY = Instruction(6634, "worldmap_disableelementcategory")
        val WORLDMAP_GETDISABLEELEMENTS = Instruction(6635, "worldmap_getdisableelements")
        val WORLDMAP_GETDISABLEELEMENT = Instruction(6636, "worldmap_getdisableelement")
        val WORLDMAP_GETDISABLEELEMENTCATEGORY = Instruction(6637, "worldmap_getdisableelementcategory")
        val WORLDMAP_LISTELEMENT_START = Instruction(6639, "worldmap_listelement_start")
        val WORLDMAP_LISTELEMENT_NEXT = Instruction(6640, "worldmap_listelement_next")
        val MEC_TEXT = Instruction(6693, "mec_text")
        val MEC_TEXTSIZE = Instruction(6694, "mec_textsize")
        val MEC_CATEGORY = Instruction(6695, "mec_category")
        val MEC_SPRITE = Instruction(6696, "mec_sprite")

        init {
            val instructions = setOf(
                ICONST,
                GET_VARP,
                SET_VARP,
                SCONST,
                JUMP,
                IF_ICMPNE,
                IF_ICMPEQ,
                IF_ICMPLT,
                IF_ICMPGT,
                RETURN,
                GET_VARBIT,
                SET_VARBIT,
                IF_ICMPLE,
                IF_ICMPGE,
                ILOAD,
                ISTORE,
                SLOAD,
                SSTORE,
                JOIN_STRING,
                POP_INT,
                POP_STRING,
                INVOKE,
                GET_VARC_INT,
                SET_VARC_INT,
                DEFINE_ARRAY,
                GET_ARRAY_INT,
                SET_ARRAY_INT,
                GET_VARC_STRING_OLD,
                SET_VARC_STRING_OLD,
                GET_VARC_STRING,
                SET_VARC_STRING,
                SWITCH,
                CC_CREATE,
                CC_DELETE,
                CC_DELETEALL,
                CC_FIND,
                IF_FIND,
                CC_SETPOSITION,
                CC_SETSIZE,
                CC_SETHIDE,
                CC_SETNOCLICKTHROUGH,
                CC_SETSCROLLPOS,
                CC_SETCOLOUR,
                CC_SETFILL,
                CC_SETTRANS,
                CC_SETLINEWID,
                CC_SETGRAPHIC,
                CC_SET2DANGLE,
                CC_SETTILING,
                CC_SETMODEL,
                CC_SETMODELANGLE,
                CC_SETMODELANIM,
                CC_SETMODELORTHOG,
                CC_SETTEXT,
                CC_SETTEXTFONT,
                CC_SETTEXTALIGN,
                CC_SETTEXTSHADOW,
                CC_SETOUTLINE,
                CC_SETGRAPHICSHADOW,
                CC_SETVFLIP,
                CC_SETHFLIP,
                CC_SETSCROLLSIZE,
                CC_RESUME_PAUSEBUTTON,
                CC_SETFILLCOLOUR,
                CC_SETLINEDIRECTION,
                CC_SETOBJECT,
                CC_SETNPCHEAD,
                CC_SETPLAYERHEAD_SELF,
                CC_SETOBJECT_NONUM,
                CC_SETOBJECT_ALWAYS_NUM,
                CC_SETOP,
                CC_SETDRAGGABLE,
                CC_SETDRAGGABLEBEHAVIOR,
                CC_SETDRAGDEADZONE,
                CC_SETDRAGDEADTIME,
                CC_SETOPBASE,
                CC_SETTARGETVERB,
                CC_CLEAROPS,
                CC_SETONCLICK,
                CC_SETONHOLD,
                CC_SETONRELEASE,
                CC_SETONMOUSEOVER,
                CC_SETONMOUSELEAVE,
                CC_SETONDRAG,
                CC_SETONTARGETLEAVE,
                CC_SETONVARTRANSMIT,
                CC_SETONTIMER,
                CC_SETONOP,
                CC_SETONDRAGCOMPLETE,
                CC_SETONCLICKREPEAT,
                CC_SETONMOUSEREPEAT,
                CC_SETONINVTRANSMIT,
                CC_SETONSTATTRANSMIT,
                CC_SETONTARGETENTER,
                CC_SETONSCROLLWHEEL,
                CC_SETONCHATTRANSMIT,
                CC_SETONKEY,
                CC_SETONFRIENDTRANSMIT,
                CC_SETONCLANTRANSMIT,
                CC_SETONMISCTRANSMIT,
                CC_SETONDIALOGABORT,
                CC_SETONSUBCHANGE,
                CC_SETONSTOCKTRANSMIT,
                CC_SETONRESIZE,
                CC_GETX,
                CC_GETY,
                CC_GETWIDTH,
                CC_GETHEIGHT,
                CC_GETHIDE,
                CC_GETLAYER,
                CC_GETSCROLLX,
                CC_GETSCROLLY,
                CC_GETTEXT,
                CC_GETSCROLLWIDTH,
                CC_GETSCROLLHEIGHT,
                CC_GETMODELZOOM,
                CC_GETMODELANGLE_X,
                CC_GETMODELANGLE_Z,
                CC_GETMODELANGLE_Y,
                CC_GETTRANS,
                CC_GETCOLOUR,
                CC_GETFILLCOLOUR,
                CC_GETINVOBJECT,
                CC_GETINVCOUNT,
                CC_GETID,
                CC_GETTARGETMASK,
                CC_GETOP,
                CC_GETOPBASE,
                CC_CALLONRESIZE,
                IF_SETPOSITION,
                IF_SETSIZE,
                IF_SETHIDE,
                IF_SETNOCLICKTHROUGH,
                IF_SETSCROLLPOS,
                IF_SETCOLOUR,
                IF_SETFILL,
                IF_SETTRANS,
                IF_SETLINEWID,
                IF_SETGRAPHIC,
                IF_SET2DANGLE,
                IF_SETTILING,
                IF_SETMODEL,
                IF_SETMODELANGLE,
                IF_SETMODELANIM,
                IF_SETMODELORTHOG,
                IF_SETTEXT,
                IF_SETTEXTFONT,
                IF_SETTEXTALIGN,
                IF_SETTEXTSHADOW,
                IF_SETOUTLINE,
                IF_SETGRAPHICSHADOW,
                IF_SETVFLIP,
                IF_SETHFLIP,
                IF_SETSCROLLSIZE,
                IF_RESUME_PAUSEBUTTON,
                IF_SETFILLCOLOUR,
                IF_SETLINEDIRECTION,
                IF_SETOBJECT,
                IF_SETNPCHEAD,
                IF_SETPLAYERHEAD_SELF,
                IF_SETOBJECT_NONUM,
                IF_SETOBJECT_ALWAYS_NUM,
                IF_SETOP,
                IF_SETDRAGGABLE,
                IF_SETDRAGGABLEBEHAVIOR,
                IF_SETDRAGDEADZONE,
                IF_SETDRAGDEADTIME,
                IF_SETOPBASE,
                IF_SETTARGETVERB,
                IF_CLEAROPS,
                IF_SETOPKEY,
                IF_SETOPTKEY,
                IF_SETOPKEYRATE,
                IF_SETOPTKEYRATE,
                IF_SETOPKEYIGNOREHELD,
                IF_SETOPTKEYIGNOREHELD,
                IF_SETONCLICK,
                IF_SETONHOLD,
                IF_SETONRELEASE,
                IF_SETONMOUSEOVER,
                IF_SETONMOUSELEAVE,
                IF_SETONDRAG,
                IF_SETONTARGETLEAVE,
                IF_SETONVARTRANSMIT,
                IF_SETONTIMER,
                IF_SETONOP,
                IF_SETONDRAGCOMPLETE,
                IF_SETONCLICKREPEAT,
                IF_SETONMOUSEREPEAT,
                IF_SETONINVTRANSMIT,
                IF_SETONSTATTRANSMIT,
                IF_SETONTARGETENTER,
                IF_SETONSCROLLWHEEL,
                IF_SETONCHATTRANSMIT,
                IF_SETONKEY,
                IF_SETONFRIENDTRANSMIT,
                IF_SETONCLANTRANSMIT,
                IF_SETONMISCTRANSMIT,
                IF_SETONDIALOGABORT,
                IF_SETONSUBCHANGE,
                IF_SETONSTOCKTRANSMIT,
                IF_SETONRESIZE,
                IF_GETX,
                IF_GETY,
                IF_GETWIDTH,
                IF_GETHEIGHT,
                IF_GETHIDE,
                IF_GETLAYER,
                IF_GETSCROLLX,
                IF_GETSCROLLY,
                IF_GETTEXT,
                IF_GETSCROLLWIDTH,
                IF_GETSCROLLHEIGHT,
                IF_GETMODELZOOM,
                IF_GETMODELANGLE_X,
                IF_GETMODELANGLE_Z,
                IF_GETMODELANGLE_Y,
                IF_GETTRANS,
                IF_GETCOLOUR,
                IF_GETFILLCOLOUR,
                IF_GETINVOBJECT,
                IF_GETINVCOUNT,
                IF_HASSUB,
                IF_GETTOP,
                IF_GETTARGETMASK,
                IF_GETOP,
                IF_GETOPBASE,
                IF_CALLONRESIZE,
                MES,
                ANIM,
                IF_CLOSE,
                RESUME_COUNTDIALOG,
                RESUME_NAMEDIALOG,
                RESUME_STRINGDIALOG,
                OPPLAYER,
                IF_DRAGPICKUP,
                CC_DRAGPICKUP,
                MOUSECAM,
                GETREMOVEROOFS,
                SETREMOVEROOFS,
                OPENURL,
                RESUME_OBJDIALOG,
                BUG_REPORT,
                SETSHIFTCLICKDROP,
                SETSHOWMOUSEOVERTEXT,
                RENDERSELF,
                SETSHOWMOUSECROSS,
                SETSHOWLOADINGMESSAGES,
                SETTAPTODROP,
                GETTAPTODROP,
                GETCANVASSIZE,
                SETHIDEUSERNAME,
                GETHIDEUSERNAME,
                SETREMEMBERUSERNAME,
                GETREMEMBERUSERNAME,
                SOUND_SYNTH,
                SOUND_SONG,
                SOUND_JINGLE,
                CLIENTCLOCK,
                INV_GETOBJ,
                INV_GETNUM,
                INV_TOTAL,
                INV_SIZE,
                STAT,
                STAT_BASE,
                STAT_XP,
                COORD,
                COORDX,
                COORDZ,
                COORDY,
                MAP_MEMBERS,
                INVOTHER_GETOBJ,
                INVOTHER_GETNUM,
                INVOTHER_TOTAL,
                STAFFMODLEVEL,
                REBOOTTIMER,
                MAP_WORLD,
                RUNENERGY_VISIBLE,
                RUNWEIGHT_VISIBLE,
                PLAYERMOD,
                WORLDFLAGS,
                MOVECOORD,
                ENUM_STRING,
                ENUM,
                ENUM_GETOUTPUTCOUNT,
                FRIEND_COUNT,
                FRIEND_GETNAME,
                FRIEND_GETWORLD,
                FRIEND_GETRANK,
                FRIEND_SETRANK,
                FRIEND_ADD,
                FRIEND_DEL,
                IGNORE_ADD,
                IGNORE_DEL,
                FRIEND_TEST,
                CLAN_GETCHATDISPLAYNAME,
                CLAN_GETCHATCOUNT,
                CLAN_GETCHATUSERNAME,
                CLAN_GETCHATUSERWORLD,
                CLAN_GETCHATUSERRANK,
                CLAN_GETCHATMINKICK,
                CLAN_KICKUSER,
                CLAN_GETCHATRANK,
                CLAN_JOINCHAT,
                CLAN_LEAVECHAT,
                IGNORE_COUNT,
                IGNORE_GETNAME,
                IGNORE_TEST,
                CLAN_ISSELF,
                CLAN_GETCHATOWNERNAME,
                CLAN_ISFRIEND,
                CLAN_ISIGNORE,
                STOCKMARKET_GETOFFERTYPE,
                STOCKMARKET_GETOFFERITEM,
                STOCKMARKET_GETOFFERPRICE,
                STOCKMARKET_GETOFFERCOUNT,
                STOCKMARKET_GETOFFERCOMPLETEDCOUNT,
                STOCKMARKET_GETOFFERCOMPLETEDGOLD,
                STOCKMARKET_ISOFFEREMPTY,
                STOCKMARKET_ISOFFERSTABLE,
                STOCKMARKET_ISOFFERFINISHED,
                STOCKMARKET_ISOFFERADDING,
                TRADINGPOST_SORTBY_NAME,
                TRADINGPOST_SORTBY_PRICE,
                TRADINGPOST_SORTFILTERBY_WORLD,
                TRADINGPOST_SORTBY_AGE,
                TRADINGPOST_SORTBY_COUNT,
                TRADINGPOST_GETTOTALOFFERS,
                TRADINGPOST_GETOFFERWORLD,
                TRADINGPOST_GETOFFERNAME,
                TRADINGPOST_GETOFFERPREVIOUSNAME,
                TRADINGPOST_GETOFFERAGE,
                TRADINGPOST_GETOFFERCOUNT,
                TRADINGPOST_GETOFFERPRICE,
                TRADINGPOST_GETOFFERITEM,
                ADD,
                SUB,
                MULTIPLY,
                DIV,
                RANDOM,
                RANDOMINC,
                INTERPOLATE,
                ADDPERCENT,
                SETBIT,
                CLEARBIT,
                TESTBIT,
                MOD,
                POW,
                INVPOW,
                AND,
                OR,
                SCALE,
                APPEND_NUM,
                APPEND,
                APPEND_SIGNNUM,
                LOWERCASE,
                FROMDATE,
                TEXT_GENDER,
                TOSTRING,
                COMPARE,
                PARAHEIGHT,
                PARAWIDTH,
                TEXT_SWITCH,
                ESCAPE,
                APPEND_CHAR,
                CHAR_ISPRINTABLE,
                CHAR_ISALPHANUMERIC,
                CHAR_ISALPHA,
                CHAR_ISNUMERIC,
                STRING_LENGTH,
                SUBSTRING,
                REMOVETAGS,
                STRING_INDEXOF_CHAR,
                STRING_INDEXOF_STRING,
                OC_NAME,
                OC_OP,
                OC_IOP,
                OC_COST,
                OC_STACKABLE,
                OC_CERT,
                OC_UNCERT,
                OC_MEMBERS,
                OC_PLACEHOLDER,
                OC_UNPLACEHOLDER,
                OC_FIND,
                OC_FINDNEXT,
                OC_FINDRESET,
                CHAT_GETFILTER_PUBLIC,
                CHAT_SETFILTER,
                CHAT_SENDABUSEREPORT,
                CHAT_GETHISTORY_BYTYPEANDLINE,
                CHAT_GETHISTORY_BYUID,
                CHAT_GETFILTER_PRIVATE,
                CHAT_SENDPUBLIC,
                CHAT_SENDPRIVATE,
                CHAT_PLAYERNAME,
                CHAT_GETFILTER_TRADE,
                CHAT_GETHISTORYLENGTH,
                CHAT_GETNEXTUID,
                CHAT_GETPREVUID,
                DOCHEAT,
                CHAT_SETMESSAGEFILTER,
                CHAT_GETMESSAGEFILTER,
                GETWINDOWMODE,
                SETWINDOWMODE,
                GETDEFAULTWINDOWMODE,
                SETDEFAULTWINDOWMODE,
                CAM_FORCEANGLE,
                CAM_GETANGLE_XA,
                CAM_GETANGLE_YA,
                CAM_SETFOLLOWHEIGHT,
                CAM_GETFOLLOWHEIGHT,
                LOGOUT,
                VIEWPORT_SETFOV,
                VIEWPORT_SETZOOM,
                VIEWPORT_CLAMPFOV,
                VIEWPORT_GETEFFECTIVESIZE,
                VIEWPORT_GETZOOM,
                VIEWPORT_GETFOV,
                WORLDLIST_FETCH,
                WORLDLIST_START,
                WORLDLIST_NEXT,
                WORLDLIST_SPECIFIC,
                WORLDLIST_SORT,
                SETFOLLOWEROPSLOWPRIORITY,
                NC_PARAM,
                LC_PARAM,
                OC_PARAM,
                STRUCT_PARAM,
                ON_MOBILE,
                CLIENTTYPE,
                BATTERYLEVEL,
                BATTERYCHARGING,
                WIFIAVAILABLE,
                WORLDMAP_GETMAPNAME,
                WORLDMAP_SETMAP,
                WORLDMAP_GETZOOM,
                WORLDMAP_SETZOOM,
                WORLDMAP_ISLOADED,
                WORLDMAP_JUMPTODISPLAYCOORD,
                WORLDMAP_JUMPTODISPLAYCOORD_INSTANT,
                WORLDMAP_JUMPTOSOURCECOORD,
                WORLDMAP_JUMPTOSOURCECOORD_INSTANT,
                WORLDMAP_GETDISPLAYPOSITION,
                WORLDMAP_GETCONFIGORIGIN,
                WORLDMAP_GETCONFIGSIZE,
                WORLDMAP_GETCONFIGBOUNDS,
                WORLDMAP_GETCONFIGZOOM,
                WORLDMAP_GETCURRENTMAP,
                WORLDMAP_GETDISPLAYCOORD,
                WORLDMAP_COORDINMAP,
                WORLDMAP_GETSIZE,
                WORLDMAP_PERPETUALFLASH,
                WORLDMAP_FLASHELEMENT,
                WORLDMAP_FLASHELEMENTCATEGORY,
                WORLDMAP_STOPCURRENTFLASHES,
                WORLDMAP_DISABLEELEMENTS,
                WORLDMAP_DISABLEELEMENT,
                WORLDMAP_DISABLEELEMENTCATEGORY,
                WORLDMAP_GETDISABLEELEMENTS,
                WORLDMAP_GETDISABLEELEMENT,
                WORLDMAP_GETDISABLEELEMENTCATEGORY,
                WORLDMAP_LISTELEMENT_START,
                WORLDMAP_LISTELEMENT_NEXT,
                MEC_TEXT,
                MEC_TEXTSIZE,
                MEC_CATEGORY,
                MEC_SPRITE
            )
            instructions.forEach { instruction ->
                byOpcode[instruction.opcode] = instruction
                byName[instruction.name] = instruction
            }
        }
    }
}