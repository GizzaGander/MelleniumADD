package com.Mellenium.Addons.client.gui;

import com.brandon3055.draconicevolution.common.lib.References2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiBooks extends GuiScreen {
    private final int bookImageHeight = 181;
    private final int bookImageWidth = 256;
    private int currPage = 0;
    private static final int bookTotalPages = 4;
    private static ResourceLocation[] bookPageTextures = new ResourceLocation[bookTotalPages];
    private static String[] stringPageText = new String[bookTotalPages];
    private GuiButton buttonDone;
    private NextPageButton buttonNextPage;
    private NextPageButton buttonPreviousPage;

    public GuiBooks()
    {
        bookPageTextures[0] = new ResourceLocation(
                References2.MODID+":textures/gui/book.png");
        bookPageTextures[1] = new ResourceLocation(
                References2.MODID+":textures/gui/book.png");
        stringPageText[0]="The Mysterious Stranger admired your family cow and asked if it was for sale.\n\nWhen you nodded, he offered to trade some Magic Beans, that (if planted in tilled dirt) would lead to more wealth than you could imagine.";
        stringPageText[1]="So you handed him your cow, and grabbed the Magic Beans.\n\nPleased with yourself, you hurried away, looking for tilled dirt in which to plant the Magic Beans.\n\nYou couldn't wait to see how proud your mother would be for";
        stringPageText[2]="being so shrewd!  Untold wealth in return for an old, milkless cow; what a good deal you made!\n\nSo off you went, looking for a place to plant the Magic Beans with room to grow...";
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui()
    {
        // DEBUG
        System.out.println("GuiBooks initGUI()");
        buttonList.clear();
        Keyboard.enableRepeatEvents(true);

        buttonDone = new GuiButton(0, width / 2 + 2, bookImageHeight - 16,
                98, 20, I18n.format("gui.done", new Object[0]));

        buttonList.add(buttonDone);
        int offsetFromScreenLeft = (width - bookImageWidth) / 2;
        int offsetFromScreenUp = (height - bookImageHeight) / 2;
        buttonList.add(buttonNextPage = new NextPageButton(1,
                offsetFromScreenLeft + 120, offsetFromScreenUp + 156, true));
        buttonList.add(buttonPreviousPage = new NextPageButton(2,
                offsetFromScreenLeft + 38, offsetFromScreenUp + 156, false));
    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen()
    {
        buttonDone.visible = (currPage == bookTotalPages - 1);
        buttonNextPage.visible = (currPage < bookTotalPages - 1);
        buttonPreviousPage.visible = currPage > 0;
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int parWidth, int parHeight, float p_73863_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (currPage == 0)
        {
            mc.getTextureManager().bindTexture(bookPageTextures[0]);
        }
        else
        {
            mc.getTextureManager().bindTexture(bookPageTextures[1]);
        }
        int offsetFromScreenLeft = (width - bookImageWidth ) / 2;
        int offsetFromScreenUp = (height - bookImageHeight) / 2;
        drawTexturedModalRect(offsetFromScreenLeft, offsetFromScreenUp, 0, 0, bookImageWidth,
                bookImageHeight);
        int widthOfString;
        String stringPageIndicator = I18n.format("book.pageIndicator",
                new Object[] {Integer.valueOf(currPage + 1), bookTotalPages});
        widthOfString = fontRendererObj.getStringWidth(stringPageIndicator);
        fontRendererObj.drawString(stringPageIndicator,
                offsetFromScreenLeft - widthOfString + bookImageWidth - 132,
                offsetFromScreenUp + bookImageHeight - 15, 0);
        fontRendererObj.drawSplitString(stringPageText[currPage],
                offsetFromScreenLeft + 18, offsetFromScreenUp + 7, 104, 0);
        try {
            fontRendererObj.drawSplitString(stringPageText[currPage + 1],
                    offsetFromScreenLeft + 138, offsetFromScreenUp + 7, 104, 0);
        }
        catch (NullPointerException e){
            fontRendererObj.drawSplitString(stringPageText[currPage],
                    offsetFromScreenLeft + 18, offsetFromScreenUp + 7, 104, 0);
        }
        super.drawScreen(parWidth, parHeight, p_73863_3_);

    }

    /**
     * Called when a mouse button is pressed and the mouse is moved around.
     * Parameters are : mouseX, mouseY, lastButtonClicked &
     * timeSinceMouseClick.
     */
    @Override
    protected void mouseClickMove(int parMouseX, int parMouseY,
                                  int parLastButtonClicked, long parTimeSinceMouseClick)
    {

    }

    @Override
    protected void actionPerformed(GuiButton parButton)
    {
        if (parButton == buttonDone)
        {
            // You can send a packet to server here if you need server to do
            // something
            mc.displayGuiScreen((GuiScreen)null);
        }
        else if (parButton == buttonNextPage)
        {
            if (currPage < bookTotalPages - 1)
            {
                ++currPage;
            }
        }
        else if (parButton == buttonPreviousPage)
        {
            if (currPage > 0)
            {
                --currPage;
            }
        }
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat
     * events
     */
    @Override
    public void onGuiClosed()
    {

    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in
     * single-player
     */
    @Override
    public boolean doesGuiPauseGame()
    {
        return true;
    }

    @SideOnly
    (Side.CLIENT)
    static class NextPageButton extends GuiButton
    {
        private final boolean isNextButton;

        public NextPageButton(int parButtonId, int parPosX, int parPosY,
                              boolean parIsNextButton)
        {
            super(parButtonId, parPosX, parPosY, 23, 13, "");
            isNextButton = parIsNextButton;
        }

        /**
         * Draws this button to the screen.
         */
        @Override
        public void drawButton(Minecraft mc, int parX, int parY)
        {
            if (visible)
            {
                boolean isButtonPressed = (parX >= xPosition
                        && parY >= yPosition
                        && parX < xPosition + width
                        && parY < yPosition + height);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(bookPageTextures[1]);
                int textureX = 0;
                int textureY = 192;

                if (isButtonPressed)
                {
                    textureX += 23;
                }

                if (!isNextButton)
                {
                    textureY += 13;
                }

                drawTexturedModalRect(xPosition, yPosition,
                        textureX, textureY,
                        23, 13);
            }
        }
    }
}