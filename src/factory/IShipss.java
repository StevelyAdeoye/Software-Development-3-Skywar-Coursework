package factory;


public interface IShipss Object create(){
{
	if(shipType == BattleCruiserFactory)
	{
		IShips =  new BattleCruiser();

	}
	
	if(shipType == BattleShooterfactory)
	{
		Iships = new BattleShooter();
		
	}
	
	if(shipType == BattleStarfactory)
	{
		Iships = new BattleStar();
		
	}
	
	return IShipss;
}
}

