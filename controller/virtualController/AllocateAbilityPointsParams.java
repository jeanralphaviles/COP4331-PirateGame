/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.virtualController;

import model.entity.occupation.ability.Ability;

/**
 *
 * @author comcc_000
 */
public class AllocateAbilityPointsParams {
    
    public Ability ability;
    public int points;
    
    public AllocateAbilityPointsParams(Ability ability, int points) {
        this.ability = ability;
        this.points = points;
    }
    
}
