/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwer0.modtools.inventories.permortemp;

import com.lwer0.modtools.ModTools;
import com.lwer0.modtools.inventories.MainInventory;
import static com.lwer0.modtools.inventories.MainInventory.maininv;
import com.lwer0.modtools.utils.ColorUtil;
import static com.lwer0.modtools.utils.ColorUtil.color;
import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

/**
 *
 * @author Joel
 */
public class PermOrTempBan implements Listener {
    
    private static String name;

    public PermOrTempBan(ModTools aThis) {
    }

    public PermOrTempBan() {
    }
    
    public void setName(String pname) {
        PermOrTempBan.name = pname;
    }
    
    public static Inventory banpermortempinv = Bukkit.createInventory(null, 27, ColorUtil.color("&2ModTools - Perm or Temp"));
    static {
        for(Player players : Bukkit.getOnlinePlayers()) {
            if(!players.getName().equals(name)) {
                
                    List<String> lorename = new ArrayList<String>();
                    lorename.add(color("&cJugador el cual"));
                    lorename.add(color("&cva a ser baneado"));
 
                    String pl = players.getName();
                    ItemStack skull = new ItemStack(Material.SKULL_ITEM);
                    skull.setDurability((short)3);
                    SkullMeta sm = (SkullMeta) skull.getItemMeta();
                    sm.setOwner(pl);
                    sm.setDisplayName(ChatColor.AQUA + "" + pl);
                    sm.setLore(lorename);
                    skull.setItemMeta(sm);
 
                    banpermortempinv.setItem(4, skull);
            }
         }
        //Item 12
        List<String> list1 = new ArrayList<String>();
        ItemStack ns1 = new ItemStack (Material.BEDROCK, 1);
        ItemMeta im1 = ns1.getItemMeta();
        im1.setDisplayName(color("&4Permanent Ban"));
        im1.setLore(list1);
        ns1.setItemMeta(im1);
        //Item 14
        List<String> list2 = new ArrayList<String>();
        ItemStack ns2 = new ItemStack (Material.FIREWORK_CHARGE, 1);
        ItemMeta im2 = ns2.getItemMeta();
        im2.setDisplayName(color("&cTemporal Ban"));
        im2.setLore(list2);
        ns2.setItemMeta(im2);
        
        banpermortempinv.setItem(12, ns1);
        banpermortempinv.setItem(14, ns2);
    }     
    @EventHandler
    public void InvClick (InventoryClickEvent event) {
        Player p2 = Bukkit.getServer().getPlayer(name);
        Player p = (Player)event.getWhoClicked();
        if (event.getClickedInventory().equals(banpermortempinv)) {
            if (event.getSlot() == 12) {
                p2.setBanned(true);
                p2.kickPlayer(color("&cHas sido baneado permanentemente por no respetar las normas de HideOutMC"));
            } else if (event.getSlot() == 14) {
                p2.kickPlayer(color("&cHas sido expulsado por no respetar las normas de HideOutMC"));
            }
        }
    }
}
