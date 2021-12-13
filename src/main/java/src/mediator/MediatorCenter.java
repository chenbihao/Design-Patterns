package src.mediator;

/**
 * @author: chenbihao
 * @create: 2021/12/13
 * @Description: 中介者实现：车辆呼叫中心
 */
public class MediatorCenter implements Mediator {

    private ComponentUser user;
    private ComponentCar car;
    private ComponentVan van;
    private ComponentTruck truck;


    @Override
    public void registerComponent(Component component) {
        switch (component.getType()) {
            case USER:
                user = (ComponentUser) component;
                break;
            case CAR:
                car = (ComponentCar) component;
                break;
            case VAN:
                van = (ComponentVan) component;
                break;
            case TRUCK:
                truck = (ComponentTruck) component;
                break;
            default:
        }
    }

    @Override
    public String callTheCar(Type type) {
        switch (type) {
            case CAR:
                if (car != null) {
                    car.charge();
                    return "呼叫小车成功";
                }
            case VAN:
                if (van != null) {
                    van.charge();
                    return "呼叫面包车成功";
                }
            case TRUCK:
                if (truck != null) {
                    truck.charge();
                    return "呼叫货车成功";
                }
            default:
                return "暂无车辆可调度";
        }
    }

    @Override
    public void charge(int paymentAmount) {
        user.deduct(paymentAmount);
    }

    public int getMoneyByType(Type type){
        switch (type) {
            case USER:
                return user.getMoney();
            case CAR:
                return car.getMoney();
            case VAN:
                return van.getMoney();
            case TRUCK:
                return truck.getMoney();
            default:
                return 999;
        }
    }

}
