package com.study.java8.stream;

/**
 * @author SuJi, Lee
 *
 */
public class DishMain {
	public static void main(String[] args) {
		Order s = new Order();
		s.printRelatedCaloric(s.getLowCaloricDishNames());
		s.printRelatedCaloric(s.getLowCaloricDishNames2());
		s.printRelatedCaloric(s.getHighCaloricDishNames());
		System.out.println(s.getCount());
		s.printVegetarianDishes(s.getVegetarianDishes());
		s.printNumbers();
		s.printSkipDishes();
		s.getMeatsMenus();
		System.out.println(s.getWordLength());
		System.out.println(s.getDishesNameLength());
		System.out.println(s.getDistinctWords());
		System.out.println(s.getSquareRootList());
		s.printPairList(s.getNumberPairList());
		s.existVegetarian();
		System.out.println(s.allHealthy());
		System.out.println(s.noneHealthy());
		s.getVegetarianRandomMenu();
	}

}
