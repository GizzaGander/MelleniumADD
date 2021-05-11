package com.Mellenium.Addons.Thaum;


import java.util.HashMap;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigResearch;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class MelTab //Вместо "*" пишите название своего класса (Лучше при создании его не изменять). Не имеет "extends". Это класс самой вкладки.
{
        public static final String MT = "MT"; //Не обязательно.

        public static HashMap<String, Object> recipeList = new HashMap();
        public static final ResourceLocation[] backgrounds = new ResourceLocation[]{new ResourceLocation("melads", "textures/thaumcraft/page/background.png")};

        public static void init() {
                initCategories();
                initEldResearch();
                initMelResearch();
                ConfigResearch.init();
        }

        private static void initCategories() {

                {
                        ResearchCategories.registerCategory((String) "MELLTAB", (ResourceLocation) new ResourceLocation("melads", "textures/mellenium/items/icon.png"), (ResourceLocation) backgrounds[0]);
                }
        }

        private static void initEldResearch() {
                (new ResearchItem("DRPSI", "ELDRITCH", new AspectList().add(Aspect.AIR, 6).add(Aspect.MAGIC, 3), 3, 0, 0, new ResourceLocation("melads", "textures/mellenium/items/icon.png"))).setPages(new ResearchPage[]{new ResearchPage("mt.research_page.DRPSI.1")}).setRound().setConcealed().setParents(new String[]{"ELDRITCHMINOR"}).setSpecial().registerResearchItem();
        }
        private static void initMelResearch() {
                (new ResearchItem("DECRYPTER", "MELLTAB", new AspectList().add(Aspect.AIR, 6).add(Aspect.MAGIC, 3), 0, 0, 0, new ResourceLocation("melads", "textures/mellenium/blocks/decrypter.png"))).setPages(new ResearchPage[]{new ResearchPage("mt.research_page.DRPSI.2")}).setRound().setConcealed().setParents(new String[]{"DRPSI"}).setSpecial().registerResearchItem();
        }
}