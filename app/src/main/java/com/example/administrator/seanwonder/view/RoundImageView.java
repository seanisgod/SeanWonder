package com.example.administrator.seanwonder.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.administrator.seanwonder.R;

//圆形头像
public class RoundImageView extends ImageView {
    // 定义图片类型，圆形或者圆角
    private int type;
    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_ROUND = 1;
    // 圆角大小
    private int borderRadius;
    // 画笔
    private Paint paint;
    // 圆角半径
    private int radius;
    // 矩阵
    private Matrix matrix;
    // 渲染
    private BitmapShader bitmapShader;
    // 视图的宽度
    private int width;
    // 矩形
    private RectF rectF;

    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        matrix = new Matrix();
        // 获取样式属性值数组
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        // 获取圆角半径,默认圆角半径10
        borderRadius = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_borderRadius,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
        // 获取类型，默认为圆形
        type = typedArray.getInt(R.styleable.RoundImageView_type, TYPE_CIRCLE);
        // 回收TypedArray对象
        typedArray.recycle();
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 如果是圆形模式，强制把视图改成宽高一致
        if (type == TYPE_CIRCLE) {
            width = Math.min(getMeasuredWidth(), getMeasuredHeight());
            radius = width / 2;
            // 设置宽高
            setMeasuredDimension(width, width);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 获取资源文件图片
        Drawable drawable = getDrawable();
        // 如果没获取到图片，就返回
        if (drawable == null) {
            return;
        }
        // 创建二进制位图对象
        Bitmap bitmap = null;
        // 如果drawable是BitmapDrawable的一个实例
        if (drawable instanceof BitmapDrawable) {
            // 向下转型
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            // 获取位图
            bitmap = bitmapDrawable.getBitmap();
        } else {
            // 获取图片宽度
            int w = drawable.getIntrinsicWidth();
            // 获取图片高度
            int h = drawable.getIntrinsicHeight();
            // 根据宽高创建一个Bitmap对象
            Bitmap bitmapTemple = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            // 根据位图创建画布
            canvas = new Canvas(bitmapTemple);
            // 设置图片的边界位置
            drawable.setBounds(0, 0, w, h);
            // 画布上画图
            drawable.draw(canvas);
            bitmap = bitmapTemple;
        }

        // 创建渲染对象
        bitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        // 缩放级别
        float scale = 1.0f;
        if (type == TYPE_CIRCLE) {
            int minWidth = Math.min(bitmap.getWidth(), bitmap.getHeight());
            scale = width * 1.0f / minWidth;
        } else if (type == TYPE_ROUND) {
            // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
            if (!(bitmap.getWidth() == getWidth() && bitmap.getHeight() == getHeight())) {
                scale = Math.max(getWidth() * 1.0f / bitmap.getWidth(), getHeight() * 1.0f / bitmap.getHeight());
            }
        }
        // 设置缩放级别
        matrix.setScale(scale, scale);
        // 设置渲染的变换矩阵
        bitmapShader.setLocalMatrix(matrix);
        // 设置画笔的渲染
        paint.setShader(bitmapShader);
        if (type == TYPE_ROUND) {
            // 画矩形
            canvas.drawRoundRect(rectF, borderRadius, borderRadius, paint);
        } else {
            // 画圆
            canvas.drawCircle(radius, radius, radius, paint);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 圆角图片的范围
        if (type == TYPE_ROUND) {
            rectF = new RectF(0, 0, getWidth(), getHeight());
        }
    }

    // 如果内存不足，而恰好我们的Activity置于后台，不幸被重启，或者用户旋转屏幕造成Activity重启,保存之前的属性
    private static final String STATE_INSTANCE = "state_instance";
    private static final String STATE_TYPE = "state_type";
    private static final String STATE_BORDER_RADIUS = "state_border_radius";

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(STATE_TYPE, type);
        bundle.putInt(STATE_BORDER_RADIUS, borderRadius);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(((Bundle) state).getParcelable(STATE_INSTANCE));
            this.type = bundle.getInt(STATE_TYPE);
            this.borderRadius = bundle.getInt(STATE_BORDER_RADIUS);
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    public void setBorderRadius(int borderRadius) {
        int pxVal = dp2px(borderRadius);
        if (this.borderRadius != pxVal) {
            this.borderRadius = pxVal;
            invalidate();
        }
    }

    public void setType(int type) {
        if (this.type != type) {
            this.type = type;
            if (this.type != TYPE_ROUND && this.type != TYPE_CIRCLE) {
                this.type = TYPE_CIRCLE;
            }
            requestLayout();
        }
    }

    public int getType() {
        return this.type;
    }

    public int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }

}
