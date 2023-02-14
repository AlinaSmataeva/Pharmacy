package org.smataeva.finalproject.command;

import org.smataeva.finalproject.command.impl.*;

public enum CommandType {
    SIGN_IN_COMMAND(new SignInCommand()),
    SIGN_UP_COMMAND(new SignUpCommand()),
    SIGN_OUT_COMMAND(new SignOutCommand()),
    GO_TO_MAIN_PAGE_COMMAND(new GoToMainPageCommand()),
    GO_TO_SIGN_IN_PAGE_COMMAND(new GoToSignInPageCommand()),
    GO_TO_SIGN_UP_PAGE_COMMAND(new GoToSignUpPageCommand()),
    GO_TO_MEDICATION_LIST_PAGE_COMMAND(new GoToMedicationListPageCommand()),
    GO_TO_PROFILE_PAGE_COMMAND(new GoToProfilePageCommand()),
    SET_MEDICATION_PAGE_COMMAND(new SetMedicationPageCommand()),
    GO_TO_RESERVATION_PAGE_COMMAND(new GoToReservationPageCommand()),
    RESERVATION_COMMAND(new ReservationCommand()),
    ADD_MEDICATION_COMMAND(new AddMedicationCommand()),
    ADD_MEDICATION_WITH_RECIPE_COMMAND(new AddMedicationWithRecipeCommand()),
    UPDATE_RECIPE_COMMAND(new UpdateRecipeCommand()),
    GO_TO_RECIPES_PAGE_COMMAND(new GoToRecipesPageCommand()),
    GO_TO_RECIPES_PAGE_COMMAND_2(new GoToRecipesPageWithoutAttrCommand()),
    GO_TO_BASKET_PAGE_COMMAND(new GoToBasketPageCommand()),
    DELETE_ALL_FROM_BASKET_PAGE_COMMAND(new DeleteAllFromBasketCommand()),
    ADD_RECIPES_COMMAND(new AddRecipesCommand()),
    GO_TO_VISITS_PAGE_COMMAND(new GoToVisitsPageCommand());
    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return this.command;
    }
}
