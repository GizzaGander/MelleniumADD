package com.Mellenium.Addons.Thaum;


import java.util.HashMap;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchCategories;

public class MelTab //Вместо "*" пишите название своего класса (Лучше при создании его не изменять). Не имеет "extends". Это класс самой вкладки.
        {
public static final String MT = "MT"; //Не обязательно.

public static HashMap<String, Object> recipeList = new HashMap();
public static final ResourceLocation[] backgrounds = new ResourceLocation[]{new ResourceLocation("melads","textures/thaumcraft/page/background.png") /* Местоположение фона для вкладки */};

public static void setupResearchPages() {
        ResearchCategories.registerCategory((String)"MELLTAB" /* ID вашей вкладки. Обязательно писать большими буквами!!! */, (ResourceLocation)new ResourceLocation("melads","textures/mellenium/items/icon.png") /* Местоположение иконки вкладки */, (ResourceLocation)backgrounds[0]);
        }

        }
