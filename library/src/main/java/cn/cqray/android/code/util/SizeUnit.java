package cn.cqray.android.code.util;

import android.util.TypedValue;

/**
 * 尺寸单位
 * @author Cqray
 */
public enum SizeUnit {

    /** 原始像素 **/
    PX(TypedValue.COMPLEX_UNIT_PX),
    /** 设备无关的像素 **/
    DP(TypedValue.COMPLEX_UNIT_DIP),
    /** 缩放像素 **/
    SP(TypedValue.COMPLEX_UNIT_SP),
    /** 点 **/
    PT(TypedValue.COMPLEX_UNIT_PT),
    /** 英寸 **/
    IN(TypedValue.COMPLEX_UNIT_IN),
    /** 毫米 **/
    MM(TypedValue.COMPLEX_UNIT_MM);

    public final int unit;
    SizeUnit(int unit) {
        this.unit = unit;
    }
}
