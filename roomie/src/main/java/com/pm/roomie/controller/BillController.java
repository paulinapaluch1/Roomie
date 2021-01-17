package com.pm.roomie.controller;

import com.pm.roomie.dao.BillRepository;
import com.pm.roomie.dao.BillTypeRepository;
import com.pm.roomie.dao.FlatMemberRepository;
import com.pm.roomie.dao.FlatRepository;
import com.pm.roomie.dao.MembersBillRepository;
import com.pm.roomie.dao.TimetableRepository;
import com.pm.roomie.dao.UserRepository;
import com.pm.roomie.dtos.BillObject;
import com.pm.roomie.json.Bill;
import com.pm.roomie.json.BillType;
import com.pm.roomie.json.FlatMember;
import com.pm.roomie.json.MembersBill;
import com.pm.roomie.json.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobile")
public class BillController {
    
    @Autowired
    BillRepository billRepository;
    
    @Autowired
    BillTypeRepository billTypeRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    FlatRepository flatRepository;

    @Autowired
    FlatMemberRepository flatMemberRepository;
    
    @Autowired
    MembersBillRepository membersBillRepository;
    
    @GetMapping("getBills/{userId}")
    public List<Bill> getBills(@PathVariable Integer userId) {
        
        User user = userRepository.findUserById(userId);
        FlatMember member = flatMemberRepository.findByUser(user).get(0);
        List<Bill> billsList = billRepository.findByFlat(member.getFlat());
        
        BillType billType;
        
        return billsList;
    }
    
    @GetMapping("getBillsDetails/{id}")
    public Bill getBillsDetails(@PathVariable Integer id) {

        Bill bill = billRepository.findBillById(id);
        
        return bill;
    }
    
    @GetMapping("getUserBills/{userId}")
    public List<MembersBill> getUserBills(@PathVariable Integer userId) {
        User user = userRepository.findUserById(userId);
        FlatMember member = flatMemberRepository.findByUser(user).get(0);
        List<MembersBill> membersBillsList = membersBillRepository.findByFlatMember(member);
        
        return membersBillsList;
    }
    
    
    @PostMapping("saveBill")
    public Boolean saveBill(@RequestBody BillObject bill) {

        Bill billDb = billRepository.findBillById(bill.getId());

        if(billDb == null){
            Bill newBill = new Bill();
            newBill.setAmount(Float.parseFloat(bill.getAmount()));
            newBill.setBillDate(bill.getBillDate());
            newBill.setBillType(billTypeRepository.findBillTypeById(Integer.parseInt(bill.getBillType())));
            newBill.setComment(bill.getComment());
            newBill.setFlat(flatRepository.findFlatById(bill.getFlat()));

            billRepository.save(newBill);
            return true;
        }else{
            return false;
        }

    }
    
    @GetMapping("getBillById/{id}")
    public BillObject getBillById(@PathVariable Integer id) {
        Bill bill = billRepository.findBillById(id);
        
        BillObject billObject = new BillObject();
        billObject.setId(bill.getId());
        billObject.setAmount(String.valueOf(bill.getAmount()));
        billObject.setBillDate(bill.getBillDate());
        billObject.setBillType(bill.getBillType().getType());
        billObject.setComment(bill.getComment());
        billObject.setFlat(bill.getFlat().getId());
        
        return billObject;
    }

    @PostMapping("updateBill")
    public Boolean updateBill(@RequestBody Bill bill) {
        Bill billDb = billRepository.findBillById(bill.getId());
        if(billDb == null){
            return false;
        }else{
            billDb.setBillType(bill.getBillType());
            billDb.setAmount(bill.getAmount());
            billDb.setFlat(bill.getFlat());
            billDb.setBillDate(bill.getBillDate());
            billDb.setComment(bill.getComment());
            
            billRepository.save(billDb);
            return true;
        }

    }
    
}
