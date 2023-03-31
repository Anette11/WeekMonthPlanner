package com.example.weekmonthplanner.utils;

import android.text.format.DateUtils;

import com.example.domain.local.Exercise;
import com.example.weekmonthplanner.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeekCreator {

    public List<DateItem> createDateItems() {
        List<DateItem> list;
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                list = createList(0);
                break;
            case Calendar.TUESDAY:
                list = createList(-1);
                break;
            case Calendar.WEDNESDAY:
                list = createList(-2);
                break;
            case Calendar.THURSDAY:
                list = createList(-3);
                break;
            case Calendar.FRIDAY:
                list = createList(-4);
                break;
            case Calendar.SATURDAY:
                list = createList(-5);
                break;
            case Calendar.SUNDAY:
                list = createList(-6);
                break;
            default:
                list = new ArrayList<>();
                break;
        }
        return list;
    }

    private List<DateItem> createList(
            int daysToAdd
    ) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE", Locale.ENGLISH);
        List<DateItem> list = new ArrayList<>();
        calendar.add(Calendar.DATE, daysToAdd);
        for (int i = daysToAdd; i <= (daysToAdd + 6); i++) {
            if (i != daysToAdd) calendar.add(Calendar.DATE, 1);
            int colorInt = R.color.black_medium;
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                colorInt = R.color.black_darker;
            }
            if (DateUtils.isToday(calendar.getTime().getTime())) {
                colorInt = R.color.green_light;
            }
            list.add(new DateItem(
                    String.valueOf(calendar.get(Calendar.DATE)),
                    simpleDateFormat.format(calendar.getTime().getTime()).toUpperCase(),
                    colorInt));
        }
        return list;
    }

    public int getCurrentDayOfWeekIndex() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public String createDateString(
            int exerciseIndex,
            boolean forNextWeek
    ) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM dd", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        if (forNextWeek) calendar.add(Calendar.DATE, 7);
        String dateString;
        if (exerciseIndex >= Calendar.SUNDAY && exerciseIndex <= Calendar.SATURDAY) {
            calendar.set(Calendar.DAY_OF_WEEK, exerciseIndex);
        }
        dateString = simpleDateFormat.format(calendar.getTime());
        return dateString;
    }

    public boolean isExerciseToday(int exerciseIndex) {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == exerciseIndex;
    }

    public boolean isExerciseInFuture(int exerciseIndex) {
        return exerciseIndex > Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public boolean isExerciseInPast(int exerciseIndex) {
        return exerciseIndex < Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public List<Exercise> updateInExercisesFieldIsCompleted(
            List<Exercise> list
    ) {
        long modifiedAt = 0L;
        for (int i = 0; i < list.size(); i++) {
            Exercise exercise = list.get(i);
            if (modifiedAt < exercise.modifiedAt) modifiedAt = exercise.modifiedAt;
        }
        Calendar calendarModifiedAt = Calendar.getInstance();
        calendarModifiedAt.setTime(new Date(modifiedAt));
        calendarModifiedAt.setFirstDayOfWeek(calendarModifiedAt.getFirstDayOfWeek());
        Calendar calendarStartCurrentWeek = Calendar.getInstance();
        calendarStartCurrentWeek.setFirstDayOfWeek(calendarStartCurrentWeek.getFirstDayOfWeek());
        if (calendarModifiedAt.get(Calendar.WEEK_OF_YEAR) != calendarStartCurrentWeek.get(Calendar.WEEK_OF_YEAR)
        ) {
            List<Exercise> exercises = new ArrayList<>();
            boolean isCompleted = false;
            long modifiedAtUpdated = new Date().getTime();
            for (int i = 0; i < list.size(); i++) {
                Exercise exercise = list.get(i);
                exercises.add(new Exercise(
                        exercise.id,
                        isCompleted,
                        exercise.name,
                        modifiedAtUpdated));
            }
            return exercises;
        }
        return new ArrayList<>();
    }
}