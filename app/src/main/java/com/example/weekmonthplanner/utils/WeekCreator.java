package com.example.weekmonthplanner.utils;

import android.text.format.DateUtils;

import com.example.domain.local.Exercise;
import com.example.weekmonthplanner.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class WeekCreator {

    private final ResourcesProvider resourcesProvider;

    public WeekCreator(ResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    public List<DateItem> createDateItems() {
        List<DateItem> list;
        Calendar calendar = createCalendarWithMondayStartOfWeek();
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
        Calendar calendar = createCalendarWithMondayStartOfWeek();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE", Locale.ENGLISH);
        List<DateItem> list = new ArrayList<>();
        calendar.add(Calendar.DATE, daysToAdd);
        for (int i = daysToAdd; i <= (daysToAdd + 6); i++) {
            if (i != daysToAdd) calendar.add(Calendar.DATE, 1);
            int colorInt = R.color.black_medium;
            Map<Integer, String> map = getExercisesPlan();
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (calendar.get(Calendar.DAY_OF_WEEK) == entry.getKey()) {
                    colorInt = R.color.black_darker;
                }
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
        return createCalendarWithMondayStartOfWeek().get(Calendar.DAY_OF_WEEK);
    }

    public String createDateString(
            int exerciseIndex,
            boolean forNextWeek
    ) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM dd", Locale.ENGLISH);
        Calendar calendar = createCalendarWithMondayStartOfWeek();
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
        return createCalendarWithMondayStartOfWeek().get(Calendar.DAY_OF_WEEK) == exerciseIndex;
    }

    public boolean isExerciseInFuture(int exerciseIndex) {
        if (isExerciseToday(exerciseIndex)) return false;
        return exerciseIndex > createCalendarWithMondayStartOfWeek().get(Calendar.DAY_OF_WEEK) ||
                (exerciseIndex == Calendar.SUNDAY && createCalendarWithMondayStartOfWeek().get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY);
    }

    public boolean isExerciseInPast(int exerciseIndex) {
        if (isExerciseToday(exerciseIndex)) return false;
        return !isExerciseInFuture(exerciseIndex);
    }

    private Calendar createCalendarWithMondayStartOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar;
    }

    public List<Exercise> updateInExercisesFieldIsCompleted(
            List<Exercise> list
    ) {
        long modifiedAt = 0L;
        for (int i = 0; i < list.size(); i++) {
            Exercise exercise = list.get(i);
            if (modifiedAt < exercise.modifiedAt) modifiedAt = exercise.modifiedAt;
        }
        Calendar calendarModifiedAt = createCalendarWithMondayStartOfWeek();
        calendarModifiedAt.setTime(new Date(modifiedAt));
        Calendar calendarStartCurrentWeek = createCalendarWithMondayStartOfWeek();
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

    public Map<Integer, String> getExercisesPlan() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(Calendar.MONDAY, resourcesProvider.getString(R.string.exercise_block_monday_name));
        map.put(Calendar.WEDNESDAY, resourcesProvider.getString(R.string.exercise_block_wednesday_name));
        map.put(Calendar.FRIDAY, resourcesProvider.getString(R.string.exercise_block_friday_name));
        return map;
    }
}