package com.gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void standardItem_quality_decreases_sellin_decreases_each_day() {
        int startingSellin = 5;
        int startingQuality = 7;
        final Item standardItem = new Item("Elixir of the Mongoose", startingSellin, startingQuality);
        GildedRose subject = new GildedRose(new Item[]{standardItem});

        subject.updateQuality();

        assertEquals(startingSellin - 1, standardItem.sellIn);
        assertEquals(startingQuality - 1, standardItem.quality);
    }

    @Test
    void multiple_items_degrade_each_day() {
        Item firstItem = new Item("First Standard Item", 5, 4);
        Item secondItem = new Item("Second Standard Item", 3, 2);
        GildedRose subject = new GildedRose(new Item[]{firstItem, secondItem});

        subject.updateQuality();

        assertEquals(4, firstItem.sellIn);
        assertEquals(3, firstItem.quality);
        assertEquals(2, secondItem.sellIn);
        assertEquals(1, secondItem.quality);
    }

    @Test
    void item_quality_degrades_twice_as_fast_past_sellin_date() {
        Item item = new Item("Standard Item", -1, 4);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();


        assertEquals(2, item.quality);
    }

    @Test
    void item_quality_degrades_by_one_with_1_day_left() {
        Item item = new Item("Standard Item", 1, 4);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(3, item.quality);
    }

    @Test
    void item_quality_degrades_down_to_0() {
        Item item = new Item("Standard Item", 4, 1);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(0, item.quality);
    }

    @Test
    void item_quality_is_never_negative() {
        Item item = new Item("First Standard Item", 4, 0);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(0, item.quality);
    }

    @Test
    void aged_items_increase_in_quality_over_time() {
        Item item = new Item("Aged Brie", 5, 6);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(7, item.quality);
    }

    @Test
    void aged_item_quality_49_increases_up_to_50() {
        Item item = new Item("Aged Brie", 5, 49);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(50, item.quality);
    }

    @Test
    void quality_of_an_item_is_never_greater_than_50() {
        Item item = new Item("Aged Brie", 5, 50);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(50, item.quality);
    }

    @Test
    void aged_item_quality_increases_twice_as_fast_past_sellin_date() {
        Item item = new Item("Aged Brie", 0, 6);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(8, item.quality);
    }

    @Test
    void aged_item_quality_50_past_sellin_date_does_not_increase() {
        Item item = new Item("Aged Brie", 0, 50);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(50, item.quality);
    }

    @Test
    void legendary_items_never_have_to_be_sold() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", -1, 80);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(-1, item.sellIn);
    }

    @Test
    void legendary_items_never_decrease_in_quality() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", -1, 80);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(80, item.quality);
    }

    @Test
    void backstage_passes_increase_in_quality_as_sellIn_date_approaches() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(21, item.quality);
    }

    @Test
    void backstage_passes_increase_in_quality_by_1_when_there_are_10_days_or_less() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 48);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(49, item.quality);
    }

    @Test
    void backstage_passes_increase_in_quality_by_2_when_there_are_10_days_or_less() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(22, item.quality);
    }

    @Test
    void backstage_passes_quality_49_increase_up_to_50_when_there_are_10_days_or_less() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(50, item.quality);
    }

    @Test
    void backstage_passes_increase_in_quality_by_2_when_there_are_6_days_or_less() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 6, 46);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(48, item.quality);
    }

    @Test
    void backstage_passes_increase_in_quality_by_3_when_there_are_5_days_or_less() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(23, item.quality);
    }

    @Test
    void backstage_passes_quality_47_increase_up_to_50_when_there_are_5_days_or_less() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 47);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(50, item.quality);
    }

    @Test
    void backstage_passes_quality_49_increase_up_to_50_when_there_are_5_days_or_less() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(50, item.quality);
    }

    @Test
    void backstage_passes_quality_is_0_after_concert() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(0, item.quality);
    }


}
