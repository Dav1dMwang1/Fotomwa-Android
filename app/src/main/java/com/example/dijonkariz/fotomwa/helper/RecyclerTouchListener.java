//package com.example.dijonkariz.fotomwa.helper;
//
//import android.content.Context;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
//    private GestureDetector gestureDetector;
//    private View.OnClickListener clickListener;
//
//    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, View.OnClickListener clickListener) {
//        this.clickListener = clickListener;
//        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                return true;
//            }
//
//            @Override
//            public void onLongPress(MotionEvent e) {
//                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
//                if (child != null && clickListener != null) {
//                    clickListener.onClick(child, recyclerView.getChildPosition(child));
//                }
//            }
//        })
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//        return false;
//    }
//
//    @Override
//    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//
//    }
//
//    @Override
//    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//    }
//}