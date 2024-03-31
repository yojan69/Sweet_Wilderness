package sweet.wilderness.item.items;

import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent HONEY_AND_SALMON_STEW = createStew(12).build();
    public static final FoodComponent HONEY_PIE = createPie(8).build();


    private static FoodComponent.Builder createStew(int hunger) {
        return new FoodComponent.Builder().hunger(hunger).saturationModifier(0.6f);
    }
    private static FoodComponent.Builder createPie(int hunger) {
        return new FoodComponent.Builder().hunger(hunger).saturationModifier(0.3f);
    }
}
