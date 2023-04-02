package com.example.weekmonthplanner.utils;

import android.text.format.DateUtils;

import com.example.domain.local.Exercise;
import com.example.weekmonthplanner.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class WeekCreator {

    private final ResourcesProvider resourcesProvider;

    public WeekCreator(ResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    public List<Exercise> createExercisesAccordingToTimetable() {
        List<Exercise> list = new ArrayList<>();
        boolean isCompleted = false;
        Map<Integer, String> map = getExercisesPlanForWeek();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            list.add(new Exercise(
                    entry.getKey(),
                    isCompleted,
                    entry.getValue()));
        }
        return list;
    }

    public List<DateItem> createDateItems() {
        int dayOfWeek = getCurrentDayOfWeekIndex();
        int offset = getOffsetAccordingToCurrentDayOfWeek(dayOfWeek);
        List<Date> listOfCalendarDatesForCurrentWeek = getListOfCalendarDatesForCurrentWeek(offset);
        List<DateItem> dateItems = new ArrayList<>();
        for (int i = 0; i < listOfCalendarDatesForCurrentWeek.size(); i++) {
            Date date = listOfCalendarDatesForCurrentWeek.get(i);
            dateItems.add(new DateItem(
                    findDayOfMonthForDateItem(date),
                    findDayOfWeekForDateItem(date),
                    findColorIntForDateItem(date)));
        }
        return dateItems;
    }

    private int findColorIntForDateItem(
            Date date
    ) {
        int colorInt = R.color.black_medium;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Map<Integer, String> exercisesPlanForWeek = getExercisesPlanForWeek();
        for (Map.Entry<Integer, String> entry : exercisesPlanForWeek.entrySet()) {
            if (calendar.get(Calendar.DAY_OF_WEEK) == entry.getKey()) {
                colorInt = R.color.black_darker;
            }
        }
        if (DateUtils.isToday(calendar.getTime().getTime())) {
            colorInt = R.color.green_light;
        }
        return colorInt;
    }

    private String findDayOfMonthForDateItem(
            Date date
    ) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.valueOf(calendar.get(Calendar.DATE));
    }

    private String findDayOfWeekForDateItem(
            Date date
    ) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE", Locale.ENGLISH);
        return simpleDateFormat.format(date.getTime()).toUpperCase();
    }

    public String createDateString(
            int exerciseIndex,
            boolean forNextWeek
    ) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM dd", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        if (forNextWeek) calendar.add(Calendar.DATE, 7);
        String dateString;
        if (exerciseIndex >= Calendar.SUNDAY && exerciseIndex <= Calendar.SATURDAY) {
            calendar.set(Calendar.DAY_OF_WEEK, exerciseIndex);
        }
        dateString = simpleDateFormat.format(calendar.getTime());
        return dateString;
    }

    public List<Exercise> updateInExercisesFieldIsCompleted(
            List<Exercise> list
    ) {
        List<Exercise> exercisesUpdated = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        boolean isCompletedUpdated = false;
        for (int i = 0; i < list.size(); i++) {
            Exercise exercise = list.get(i);
            if (exercise.id != calendar.get(Calendar.DAY_OF_WEEK) &&
                    exercise.isCompleted != isCompletedUpdated
            ) {
                exercisesUpdated.add(new Exercise(
                        exercise.id,
                        isCompletedUpdated,
                        exercise.name));
            }
        }
        return exercisesUpdated;
    }

    private List<Integer> getListDaysOfWeek() {
        List<Integer> list = new ArrayList<>();
        list.add(Calendar.MONDAY);
        list.add(Calendar.TUESDAY);
        list.add(Calendar.WEDNESDAY);
        list.add(Calendar.THURSDAY);
        list.add(Calendar.FRIDAY);
        list.add(Calendar.SATURDAY);
        list.add(Calendar.SUNDAY);
        return list;
    }

    private Map<Integer, Integer> getMapDaysOfWeekWithOffset() {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> daysOfWeek = getListDaysOfWeek();
        int offset = 0;
        for (int i = 0; i < daysOfWeek.size(); i++) {
            offset -= 1;
            map.put(daysOfWeek.get(i), offset);
        }
        return map;
    }

    private int getOffsetAccordingToCurrentDayOfWeek(
            int currentDayOfWeek
    ) {
        Map<Integer, Integer> map = getMapDaysOfWeekWithOffset();
        int offset;
        try {
            offset = map.get(currentDayOfWeek);
        } catch (NullPointerException e) {
            offset = 0;
        }
        return offset;
    }

    private List<Date> getListOfCalendarDatesForCurrentWeek(
            int offset
    ) {
        List<Date> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, offset);
        List<Integer> listDaysOfWeek = getListDaysOfWeek();
        for (int i = 0; i < listDaysOfWeek.size(); i++) {
            calendar.add(Calendar.DATE, 1);
            list.add(new Date(calendar.getTime().getTime()));
        }
        return list;
    }

    private Map<Integer, String> getExercisesPlanForWeek() {
        Map<Integer, String> map = new HashMap<>();
        map.put(Calendar.MONDAY, resourcesProvider.getString(R.string.exercise_block_monday_name));
        map.put(Calendar.WEDNESDAY, resourcesProvider.getString(R.string.exercise_block_wednesday_name));
        map.put(Calendar.FRIDAY, resourcesProvider.getString(R.string.exercise_block_friday_name));
        return map;
    }

    public int getCurrentDayOfWeekIndex() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public boolean isExerciseToday(int exerciseIndex) {
        return getCurrentDayOfWeekIndex() == exerciseIndex;
    }

    public boolean isExerciseInFuture(int exerciseIndex) {
        if (isExerciseToday(exerciseIndex)) return false;
        if (exerciseIndex == Calendar.SUNDAY && getCurrentDayOfWeekIndex() != Calendar.SUNDAY) {
            return true;
        }
        return exerciseIndex > getCurrentDayOfWeekIndex();
    }

    public boolean isExerciseInPast(int exerciseIndex) {
        if (isExerciseToday(exerciseIndex)) return false;
        if (exerciseIndex == Calendar.SUNDAY && getCurrentDayOfWeekIndex() != Calendar.SUNDAY) {
            return false;
        }
        return exerciseIndex < getCurrentDayOfWeekIndex();
    }

    public boolean areAllExercisesCompleted(
            List<Exercise> exercises
    ) {
        if (exercises.isEmpty()) return true;
        Collections.sort(exercises, new Comparator<Exercise>() {
            @Override
            public int compare(Exercise exercise1, Exercise exercise2) {
                return exercise1.id - exercise2.id;
            }
        });
        Collections.reverse(exercises);
        if (exercises.get(0).isCompleted) return true;
        if (isExerciseInPast(exercises.get(0).id)) return true;
        if (exercises.size() == 1) return false;
        return isExerciseInPast(exercises.get(1).id);
    }
}