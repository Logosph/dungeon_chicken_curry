package ru.logosph.dungeon_chicken_curry.ui.custom;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.logosph.dungeon_chicken_curry.R;


public class FlexableViewGroup extends ViewGroup {

    private int spaceBetween = 0;
    private int minSpaceBetween = 0;
    private boolean firstElementPinned = false;
    private boolean lastElementPinned = false;

    public FlexableViewGroup(Context context) {
        super(context);
    }

    public FlexableViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FlexableViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {

        TypedArray typeArray = getContext().obtainStyledAttributes(attrs, R.styleable.FlexableViewGroup);

        spaceBetween = typeArray.getDimensionPixelSize(R.styleable.FlexableViewGroup_spaceBetween, 0);
        minSpaceBetween = typeArray.getDimensionPixelSize(R.styleable.FlexableViewGroup_minSpaceBetween, 0);
        firstElementPinned = typeArray.getBoolean(R.styleable.FlexableViewGroup_firstElementPinned, false);
        lastElementPinned = typeArray.getBoolean(R.styleable.FlexableViewGroup_lastElementPinned, false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int height = 0;
        int lineWidth = minSpaceBetween;
        int lineHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            lineWidth += child.getMeasuredWidth() + minSpaceBetween;
            lineHeight = Math.max(lineHeight, child.getMeasuredHeight());

            if (lineWidth > width) {
                height += lineHeight + spaceBetween;
                lineWidth = child.getMeasuredWidth() + minSpaceBetween * 2;
                lineHeight = child.getMeasuredHeight();
            }
        }

        height += lineHeight;
        height += getPaddingTop() + getPaddingBottom();

        setMeasuredDimension(width + getPaddingLeft() + getPaddingRight(), height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        List<Pair<Integer, Integer>> positions = calculateChildrenPositions(r, l, t, b);

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Pair<Integer, Integer> position = positions.get(i);
            child.layout(position.first, position.second, position.first + child.getMeasuredWidth(), position.second + child.getMeasuredHeight());
        }
    }

    protected ArrayList<Pair<Integer, Integer>> calculateChildrenPositions(int r, int l, int t, int b) {
        ArrayList<Pair<Integer, Integer>> positions = new ArrayList<>();
        int width = r - l;
        int x = getPaddingLeft();
        int y = getPaddingTop();
        int lineHeight = 0;
        int lineStartPosition = 0;
        int lenghtSum = 0;
        int lineCount = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (lenghtSum + child.getMeasuredWidth() > width || (width - lenghtSum - child.getMeasuredWidth()) / (lineCount + 2) < minSpaceBetween) {

                int spaceWidth = getSpaceWidth(width, lenghtSum, lineCount);
                setChildrenPositions(spaceWidth, positions, lineStartPosition, i, y);
//                x += spaceWidth;
//                for (int j = lineStartPosition; j < i; j++) {
//                    View childInLine = getChildAt(j);
//                    positions.add(new Pair<>(x, y));
//                    x += childInLine.getMeasuredWidth() + spaceWidth;
//                }

                // Default reinit
                x = getPaddingLeft();
                y += lineHeight + spaceBetween;
                lineHeight = 0;
                lineStartPosition = i;
                lineCount = 0;
                lenghtSum = 0;
            }
            lineCount += 1;
            lenghtSum += child.getMeasuredWidth();
            lineHeight = Math.max(lineHeight, child.getMeasuredHeight());
        }

        int spaceWidth = (width - lenghtSum) / (lineCount + 1);
        x += spaceWidth;
        for (int j = lineStartPosition; j < getChildCount(); j++) {
            View childInLine = getChildAt(j);
            positions.add(new Pair<>(x, y));
            x += childInLine.getMeasuredWidth() + spaceWidth;
        }


        return positions;
    }

    protected int getSpaceWidth(int width, int lenghtSum, int lineCount) {
        if (firstElementPinned && lastElementPinned) {
            if (lineCount == 1) {
                return (width - lenghtSum) / 2;
            } else {
                return (width - lenghtSum) / (lineCount - 1);
            }
        }
        else if (firstElementPinned || lastElementPinned) {
            return (width - lenghtSum) / (lineCount);
        }
        else {
            return (width - lenghtSum) / (lineCount + 1);
        }
    }

    private void setChildrenPositions(
            int spaceWidth,
            ArrayList<Pair<Integer, Integer>> positions,
            int fromIndex,
            int toIndex,
            int y
    ) {
        int x = getPaddingLeft() + (firstElementPinned ?  0 : spaceWidth);
        for (int j = fromIndex; j < toIndex; j++) {
            View childInLine = getChildAt(j);
            positions.add(new Pair<>(x, y));
            x += childInLine.getMeasuredWidth() + spaceWidth;
        }
    }

}
