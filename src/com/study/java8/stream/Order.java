package com.study.java8.stream;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.study.java8.stream.Dish.Type;

/**
 * @author SuJi, Lee
 *
 */
public class Order {
	public List<String> getLowCaloricDishNames() {
		List<Dish> lowCaloricDishList = new ArrayList<Dish>();

		for (Dish dish : Menu.getMenuList()) {
			if (dish.getCalories() < 400) {
				lowCaloricDishList.add(dish);
			}
		}

		Collections.sort(lowCaloricDishList, new Comparator<Dish>() {
			public int compare(Dish d1, Dish d2) {
				return Integer.compare(d1.getCalories(), d2.getCalories());
			}
		});

		List<String> lowCaloricDishNames = new ArrayList<String>();
		for (Dish dish : lowCaloricDishList) {
			lowCaloricDishNames.add(dish.getName());
		}
		return lowCaloricDishNames;
	}

	public List<String> getLowCaloricDishNames2() {
		return Menu.getMenuList().stream().filter(dish -> dish.getCalories() < 400).sorted(Comparator.comparing(Dish::getCalories)).map(Dish::getName).collect(toList());
	}

	public List<String> getHighCaloricDishNames() {
		return Menu.getMenuList().stream().filter(dish -> dish.getCalories() > 300).map(Dish::getName).limit(3).collect(toList());
	}

	public void printRelatedCaloric(List<String> menuNameList) {
		menuNameList.stream().forEach(menu -> System.out.print(menu + " "));
		System.out.println();
	}

	public long getCount() {
		return Menu.getMenuList().stream().filter(dish -> dish.getCalories() > 300).distinct().limit(3).count();
	}

	public List<Dish> getVegetarianDishes() {
		return Menu.getMenuList().stream().filter(dish -> dish.isVegetarian()).collect(toList());
	}

	public void printVegetarianDishes(List<Dish> menuNameList) {
		menuNameList.stream().forEach(dish -> System.out.print(dish.getName() + " "));
		System.out.println();
	}

	public void printNumbers() {
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
	}

	public void printSkipDishes() {
		Menu.getMenuList().stream().filter(dish -> dish.getCalories() > 300).skip(2).forEach(dish -> System.out.print(dish.getName() + " "));
		System.out.println();
	}

	public List<Dish> getMeatsMenus() {
		return Menu.getMenuList().stream().filter(dish -> dish.getType() == Type.MEAT).limit(2).collect(toList());
	}

	public List<Integer> getWordLength() {
		List<String> words = Arrays.asList("Java8", "Lamdas", "In", "Action");
		return words.stream().map(String::length).collect(toList());

	}

	public List<Integer> getDishesNameLength() {
		return Menu.getMenuList().stream().map(Dish::getName).map(String::length).collect(toList());
	}

	public List<String> getDistinctWords() {
		List<String> wordList = Arrays.asList("Hello", "World");
		//		return wordList.stream().map(word -> word.split("")).distinct().collect(toList()); // 배열 stream 반환
		//		Stream<String> words = Arrays.stream(array);
		//		return wordList.stream().map(word -> word.split("")).map(Arrays::stream).distinct().collect(toList()); // 스트림 리스트 생성
		return wordList.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct().collect(toList());
	}

	public List<Integer> getSquareRootList() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		return list.stream().map(data -> data * data).collect(toList());
	}

	public List<Integer[]> getNumberPairList() {
		List<Integer> list1 = Arrays.asList(1, 2, 3);
		List<Integer> list2 = Arrays.asList(3, 4);
		List<Integer[]> result = new ArrayList<Integer[]>();

		list1.stream().forEach(data1 -> {
			list2.stream().forEach(data2 -> {
				result.add(new Integer[] {data1, data2});
			});
		});
		return result;
		//		list1.stream().flatMap(data1 -> list2.stream().map(data2 -> new int[] {data1, data2})).collect(toList());
		//		list1.stream().flatMap(data1 -> list2.stream().filter(data2 -> (data1+data2)%3 == 0).map(data2 -> new int[] {data1, data2})).collect(toList());
	}

	public void printPairList(List<Integer[]> list) {
		for (Integer[] datas : list) {
			System.out.print("(");
			for (Integer data : datas) {
				System.out.print(data + " ");
			}
			System.out.println(")");
		}
	}

	/** 내부 쇼트서킷으로 이루어짐. limit도 유사 **/
	public void existVegetarian() {
		if (Menu.getMenuList().stream().anyMatch(Dish::isVegetarian)) { //적어도 하나의 요소와 일치
			System.out.println("exist menu");
		}
	}

	public boolean allHealthy() {
		return Menu.getMenuList().stream().allMatch(dish -> dish.getCalories() < 1000); // 모든요소와 일치
	}

	public boolean noneHealthy() {
		return Menu.getMenuList().stream().noneMatch(dish -> dish.getCalories() >= 1000); //모든 요소가 일치 하지 않아야함 <-> allMatch 
	}

	public void getVegetarianRandomMenu() { //Optional 관련 예제
		Menu.getMenuList().stream().filter(Dish::isVegetarian).findAny().ifPresent(dish -> System.out.println(dish.getName()));
		//null처리를 하지 않아도 되며, 없을 경우 아무일도 일어나지 않음 
		//Menu.getMenuList().stream().filter(Dish::isVegetarian).findAny().get(); // 있으면 반환, 없으면 NoSuchElementException
		//Menu.getMenuList().stream().filter(Dish::isVegetarian).findAny().orElse(null);
		//값이 있으면 반환, 없으면 default 값 반환
	}

}
